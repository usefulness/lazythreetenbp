package com.usefulness.threetenbp

import android.content.Context
import org.threeten.bp.jdk8.Jdk8Methods
import org.threeten.bp.zone.ZoneRules
import org.threeten.bp.zone.ZoneRulesCompat
import org.threeten.bp.zone.ZoneRulesException
import org.threeten.bp.zone.ZoneRulesProvider
import java.io.DataInputStream
import java.io.InputStream
import java.io.StreamCorruptedException
import java.util.NavigableMap
import java.util.TreeMap
import java.util.concurrent.ConcurrentSkipListMap

internal class LazyZoneRulesProvider(private val context: Context) : ZoneRulesProvider() {

    private val map = ConcurrentSkipListMap<String, ZoneRules?>()

    override fun provideZoneIds() =
        HashSet(GeneratedZoneIdsProvider.zoneIds)

    override fun provideRules(zoneId: String, forCaching: Boolean): ZoneRules {
        Jdk8Methods.requireNonNull(zoneId, "zoneId")
        var rules = map[zoneId]
        if (rules == null) {
            rules = loadData(zoneId)
            map[zoneId] = rules
        }
        return rules
    }

    override fun provideVersions(zoneId: String): NavigableMap<String, ZoneRules> {
        val versionId = GeneratedZoneIdsProvider.versionId
        val rules = provideRules(zoneId, false)

        return TreeMap(mapOf(versionId to rules))
    }

    private fun loadData(zoneId: String): ZoneRules {
        val fileName = "tzdb/$zoneId.dat"

        return runCatching { context.assets.open(fileName).use(::loadData) }
            .getOrElse { throw ZoneRulesException("Invalid binary time-zone data: $fileName", it) }
    }

    private fun loadData(inputStream: InputStream): ZoneRules {
        val dis = DataInputStream(inputStream)
        if (dis.readByte().toInt() != 1) {
            throw StreamCorruptedException("File format not recognised")
        }
        val groupId = dis.readUTF()
        if ("TZDB-ZONE" != groupId) {
            throw StreamCorruptedException("File format not recognised")
        }
        return ZoneRulesCompat.readZoneRules(dis)
    }
}
