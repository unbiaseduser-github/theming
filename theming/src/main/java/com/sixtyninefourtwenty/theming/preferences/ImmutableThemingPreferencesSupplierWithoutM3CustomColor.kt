package com.sixtyninefourtwenty.theming.preferences

import com.sixtyninefourtwenty.theming.LightDarkMode
import com.sixtyninefourtwenty.theming.ThemeColor

interface ImmutableThemingPreferencesSupplierWithoutM3CustomColor {
    val md3: Boolean
    val themeColor: ThemeColor
    val lightDarkMode: LightDarkMode
}