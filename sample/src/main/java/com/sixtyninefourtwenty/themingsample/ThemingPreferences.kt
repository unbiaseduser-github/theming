package com.sixtyninefourtwenty.themingsample

import android.os.Build
import android.os.Bundle
import androidx.core.os.BundleCompat
import com.sixtyninefourtwenty.theming.LightDarkMode
import com.sixtyninefourtwenty.theming.ThemeColor
import com.sixtyninefourtwenty.theming.preferences.ThemingPreferencesSupplier

class ThemingPreferences : ThemingPreferencesSupplier {
    override var useM3CustomColorThemeOnAndroid12: Boolean = false
    override var md3: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    override var themeColor: ThemeColor = ThemeColor.BLUE
    override var lightDarkMode: LightDarkMode = LightDarkMode.SYSTEM

    fun onSaveInstanceState(outState: Bundle) {
        with(outState) {
            putBoolean("useM3CustomColorThemeOnAndroid12", useM3CustomColorThemeOnAndroid12)
            putBoolean("md3", md3)
            putSerializable("themeColor", themeColor)
            putSerializable("lightDarkMode", lightDarkMode)
        }
    }

    fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            useM3CustomColorThemeOnAndroid12 = savedInstanceState.getBoolean("useM3CustomColorThemeOnAndroid12")
            md3 = savedInstanceState.getBoolean("md3")
            themeColor = BundleCompat.getSerializable(savedInstanceState, "themeColor", ThemeColor::class.java)!!
            lightDarkMode = BundleCompat.getSerializable(savedInstanceState, "lightDarkMode", LightDarkMode::class.java)!!
        }
    }
}