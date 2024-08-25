package com.sixtyninefourtwenty.theming

import androidx.appcompat.app.AppCompatDelegate
import org.junit.Assert.assertEquals
import org.junit.Test

class LightDarkModeApplicationTest {

    private fun testCommon(
        lightDarkMode: LightDarkMode,
        androidInt: Int
    ) {
        lightDarkMode.apply()
        assertEquals(androidInt, AppCompatDelegate.getDefaultNightMode())
    }

    @Test
    fun test() {
        LightDarkMode.entries.forEach {
            testCommon(it, when (it) {
                LightDarkMode.LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
                LightDarkMode.DARK -> AppCompatDelegate.MODE_NIGHT_YES
                LightDarkMode.BATTERY -> AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
                LightDarkMode.SYSTEM -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            })
        }
    }

}