package org.threeten.bp.zone;

import static androidx.annotation.RestrictTo.Scope.LIBRARY;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.io.DataInputStream;
import java.io.IOException;

@RestrictTo(LIBRARY)
public class ZoneRulesCompat {

    @NonNull
    public static ZoneRules readZoneRules(@NonNull DataInputStream stream) throws IOException, ClassNotFoundException {
        return StandardZoneRules.readExternal(stream);
    }

    private ZoneRulesCompat() {
        throw new AssertionError("No instances");
    }
}
