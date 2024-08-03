package com.sixtyninefourtwenty.theming.preferences

import androidx.annotation.StyleRes
import com.sixtyninefourtwenty.theming.LightDarkMode
import com.sixtyninefourtwenty.theming.ThemeColor

interface ImmutableThemingPreferencesSupplierWithoutM3CustomColor {
    val md3: Boolean
    val themeColor: ThemeColor
    val lightDarkMode: LightDarkMode

    fun determineThemeWithoutM3CustomColors(
        @StyleRes material2ThemeStyleRes: Int,
        @StyleRes material3DynamicColorsThemeStyleRes: Int
    ): Int {
        return if (!md3) {
            material2ThemeStyleRes
        } else {
            material3DynamicColorsThemeStyleRes
        }
    }

}