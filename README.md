# LazyThreeTenBp

[![Build Status](https://github.com/usefulness/lazythreetenbp/workflows/Build%20project/badge.svg)](https://github.com/usefulness/lazythreetenbp/actions)
![Maven Central](https://img.shields.io/maven-central/v/com.github.usefulness/lazythreetenbp)


A lazy loading ZoneRuleProvider for ThreeTenBp.

## Usage

You have to initialize LazyThreeTenBp as early as possible, before your code accesses any threetenbp
class. Usually the best place is in your `Application.onCreate()` method:

```kotlin
override fun onCreate() {
    super.onCreate()
    LazyThreeTen.init(this)
}
```

Afterwards you can call `LazyThreeTen.cacheZones()` on a background thread to cache the timezone
information without blocking the startup of your app. If you decide not to do that the individual
timezones will be loaded on demand when they are accessed for the first time.

## Download

Add a Gradle dependency:

```groovy
implementation "org.threeten:threetenbp:1.6.0:no-tzdb"
implementation "com.github.usefulness:lazythreetenbp:${version}"
```

## Changes

Compiler
- generate java code for list of all timezone ids
- generate a separate .dat file for each zone
- only support one timezone data version at a time (makes some things easier)

Runtime
- custom `ZoneRulesProvider`
- provides generated timezone id list
- only reads timezone from assets/disk when that timezone was requested

## Update tzdb data

1. Check for the latest tzdb version at https://www.iana.org/time-zones
2. Run `./gradlew syncTZDBDatToOutputDir -Plazythreetenbp.tzdbVersion=VERSION`
