package com.sixtyninefourtwenty.theming.preferences

/**
 * Immutable view of [ThemingPreferencesSupplier].
 */
interface ImmutableThemingPreferencesSupplier : ImmutableThemingPreferencesSupplierWithoutM3CustomColor {
    val useM3CustomColorThemeOnAndroid12: Boolean
}