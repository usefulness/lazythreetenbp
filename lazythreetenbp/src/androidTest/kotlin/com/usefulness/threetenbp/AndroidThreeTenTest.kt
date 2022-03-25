package com.usefulness.threetenbp

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.threeten.bp.zone.ZoneRulesProvider

@RunWith(AndroidJUnit4::class)
internal class AndroidThreeTenTest {

    private val context = InstrumentationRegistry.getInstrumentation().context

    @Test
    fun init() {
        LazyThreeTen.init(context)
        assertThat(ZoneRulesProvider.getAvailableZoneIds()).isNotEmpty()
    }

    @Test
    fun cache() {
        LazyThreeTen.init(context)
        LazyThreeTen.cacheZones()
    }
}
