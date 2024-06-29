package com.sixtyninefourtwenty.theming.preferences

import com.sixtyninefourtwenty.theming.LightDarkMode
import com.sixtyninefourtwenty.theming.ThemeColor

interface ThemingPreferencesSupplierWithoutM3CustomColor : ImmutableThemingPreferencesSupplierWithoutM3CustomColor {
    override var md3: Boolean
    override var themeColor: ThemeColor
    override var lightDarkMode: LightDarkMode
}