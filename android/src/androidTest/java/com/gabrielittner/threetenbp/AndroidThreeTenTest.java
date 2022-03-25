package com.gabrielittner.threetenbp;

import android.content.Context;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.threeten.bp.zone.ZoneRulesProvider;

import static com.google.common.truth.Truth.assertThat;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
public final class AndroidThreeTenTest {

    private final Context context = InstrumentationRegistry.getInstrumentation().getContext();

    @Test
    public void init() {
        LazyThreeTen.init(context);
        assertThat(ZoneRulesProvider.getAvailableZoneIds()).isNotEmpty();
    }

    @Test
    public void cache() {
        LazyThreeTen.init(context);
        LazyThreeTen.cacheZones();
    }
}
