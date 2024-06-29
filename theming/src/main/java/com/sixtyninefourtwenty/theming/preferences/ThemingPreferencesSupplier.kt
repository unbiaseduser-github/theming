package com.sixtyninefourtwenty.theming.preferences

/**
 * Represents an object that stores theming preferences. If you're using the `preference-integration`
 * extension and you want to create a default implementation, call `SharedPreferences.toThemingPreferencesSupplier`
 */
interface ThemingPreferencesSupplier : ImmutableThemingPreferencesSupplier, ThemingPreferencesSupplierWithoutM3CustomColor {
    override var useM3CustomColorThemeOnAndroid12: Boolean
}
