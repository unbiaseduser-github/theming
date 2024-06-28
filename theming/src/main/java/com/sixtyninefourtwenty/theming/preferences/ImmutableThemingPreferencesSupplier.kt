package com.sixtyninefourtwenty.theming.preferences

import com.sixtyninefourtwenty.theming.LightDarkMode
import com.sixtyninefourtwenty.theming.ThemeColor

/**
 * Immutable view of [ThemingPreferencesSupplier].
 */
interface ImmutableThemingPreferencesSupplier {
    val md3: Boolean
    val themeColor: ThemeColor
    val lightDarkMode: LightDarkMode
    val useM3CustomColorThemeOnAndroid12: Boolean
}