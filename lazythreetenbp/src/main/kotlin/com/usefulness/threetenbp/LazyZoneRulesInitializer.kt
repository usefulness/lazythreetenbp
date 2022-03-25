package com.usefulness.threetenbp

import android.app.Application
import org.threeten.bp.zone.ZoneRulesInitializer
import org.threeten.bp.zone.ZoneRulesProvider

internal class LazyZoneRulesInitializer(private val application: Application) : ZoneRulesInitializer() {

    override fun initializeProviders() {
        ZoneRulesProvider.registerProvider(LazyZoneRulesProvider(application))
    }
}
