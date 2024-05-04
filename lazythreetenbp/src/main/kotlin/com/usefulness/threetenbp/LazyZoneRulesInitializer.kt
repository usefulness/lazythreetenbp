package com.usefulness.threetenbp

import android.content.Context
import org.threeten.bp.zone.ZoneRulesInitializer
import org.threeten.bp.zone.ZoneRulesProvider

internal class LazyZoneRulesInitializer(private val context: Context) : ZoneRulesInitializer() {

    override fun initializeProviders() {
        ZoneRulesProvider.registerProvider(LazyZoneRulesProvider(context.assets))
    }
}
