package com.usefulness.threetenbp

import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.threeten.bp.zone.ZoneRulesProvider

@Config(sdk = [23, 28, 30, 31, 34])
@RunWith(RobolectricTestRunner::class)
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
