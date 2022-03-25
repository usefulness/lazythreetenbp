package org.threeten.bp.zone

import java.io.DataInputStream

internal object ZoneRulesCompat {

    fun readZoneRules(stream: DataInputStream): ZoneRules =
        StandardZoneRules.readExternal(stream)
}
