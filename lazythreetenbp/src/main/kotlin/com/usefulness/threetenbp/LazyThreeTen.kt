package com.usefulness.threetenbp

import android.app.Application
import android.content.Context
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import org.threeten.bp.zone.ZoneRulesInitializer
import org.threeten.bp.ZoneId
import org.threeten.bp.zone.ZoneRulesProvider
import java.util.concurrent.atomic.AtomicBoolean

object LazyThreeTen {

    private val INITIALIZED = AtomicBoolean()

    /**
     * Initialize threetenbp to use LazyThreeTenBp's ZoneRulesProvider
     */
    @JvmStatic
    @MainThread
    fun init(context: Context) {
        if (INITIALIZED.getAndSet(true)) {
            return
        }
        ZoneRulesInitializer.setInitializer(LazyZoneRulesInitializer((context.applicationContext as Application)))
    }

    /**
     * Call on background thread to eagerly load all zones. Starts with loading
     * [ZoneId.systemDefault] which is the one most likely to be used.
     */
    @JvmStatic
    @WorkerThread
    fun cacheZones() {
        ZoneId.systemDefault().rules
        for (zoneId in ZoneRulesProvider.getAvailableZoneIds()) {
            ZoneRulesProvider.getRules(zoneId, true)
        }
    }
}
