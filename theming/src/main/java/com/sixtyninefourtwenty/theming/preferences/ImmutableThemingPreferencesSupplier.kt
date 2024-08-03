package com.sixtyninefourtwenty.theming.preferences

import android.os.Build
import androidx.annotation.StyleRes

/**
 * Immutable view of [ThemingPreferencesSupplier].
 */
interface ImmutableThemingPreferencesSupplier : ImmutableThemingPreferencesSupplierWithoutM3CustomColor {
    val useM3CustomColorThemeOnAndroid12: Boolean

    fun determineTheme(
        @StyleRes material2ThemeStyleRes: Int,
        @StyleRes material3CustomColorsThemeStyleRes: Int,
        @StyleRes material3DynamicColorsThemeStyleRes: Int
    ): Int {
        return if (!md3) {
            material2ThemeStyleRes
        } else if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.R || useM3CustomColorThemeOnAndroid12) {
            material3CustomColorsThemeStyleRes
        } else {
            material3DynamicColorsThemeStyleRes
        }
    }

}