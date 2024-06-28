@file:Suppress("unused")

package com.sixtyninefourtwenty.theming.preferences

import com.sixtyninefourtwenty.theming.LightDarkMode
import com.sixtyninefourtwenty.theming.ThemeColor

/**
 * Represents an object that stores theming preferences. If you're using the `preference-integration`
 * extension and you want to create a default implementation, call `SharedPreferences.toThemingPreferencesSupplier`
 */
interface ThemingPreferencesSupplier : ImmutableThemingPreferencesSupplier {
    override var md3: Boolean
    override var themeColor: ThemeColor
    override var lightDarkMode: LightDarkMode
    override var useM3CustomColorThemeOnAndroid12: Boolean
}
