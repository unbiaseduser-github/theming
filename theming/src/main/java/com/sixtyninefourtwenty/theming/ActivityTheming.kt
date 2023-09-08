@file:JvmName("ActivityTheming")

package com.sixtyninefourtwenty.theming

import android.app.Activity
import android.os.Build
import androidx.annotation.StyleRes
import com.sixtyninefourtwenty.theming.preferences.ThemingPreferences

fun Activity.applyTheming(
    @StyleRes material2ThemeStyleRes: Int,
    @StyleRes material3CustomColorsThemeStyleRes: Int,
    @StyleRes material3DynamicColorsThemeStyleRes: Int
) {
    val preferences = ThemingPreferences(this)
    val themeStyleRes: Int = if (!preferences.md3) {
        material2ThemeStyleRes
    } else if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.R) {
        material3CustomColorsThemeStyleRes
    } else {
        material3DynamicColorsThemeStyleRes
    }
    setTheme(themeStyleRes)
    preferences.lightDarkMode.apply()
    preferences.themeColor.applyTo(theme)
}