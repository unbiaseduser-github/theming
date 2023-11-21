package com.sixtyninefourtwenty.theming

import androidx.appcompat.app.AppCompatDelegate

enum class LightDarkMode(private val androidInt: Int) {
    LIGHT(AppCompatDelegate.MODE_NIGHT_NO),
    DARK(AppCompatDelegate.MODE_NIGHT_YES),
    BATTERY(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY),
    SYSTEM(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

    fun apply() {
        AppCompatDelegate.setDefaultNightMode(androidInt)
    }
}