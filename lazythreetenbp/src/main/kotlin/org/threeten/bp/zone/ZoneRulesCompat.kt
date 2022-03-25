package org.threeten.bp.zone

import androidx.annotation.RestrictTo
import org.threeten.bp.zone.StandardZoneRules
import java.io.DataInputStream
import java.io.IOException
import java.lang.AssertionError

internal object ZoneRulesCompat {

    fun readZoneRules(stream: DataInputStream): ZoneRules =
        StandardZoneRules.readExternal(stream)
}
