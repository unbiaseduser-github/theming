@file:JvmName("ActivityTheming")
@file:Suppress("unused")

package com.sixtyninefourtwenty.theming

import android.app.Activity
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.StyleRes
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceGroup
import com.sixtyninefourtwenty.theming.preferences.ThemingPreferenceFragment
import com.sixtyninefourtwenty.theming.preferences.ThemingPreferences
import com.sixtyninefourtwenty.theming.preferences.addThemingPreferences

/**
 * @param preferencesFacade Preference storage to use if available. This must be one linked to
 * whatever [SharedPreferences] used by the [PreferenceFragmentCompat] that's calling
 * [PreferenceGroup.addThemingPreferences].
 *
 * If null, the default storage used by [ThemingPreferenceFragment] will be used.
 */
@JvmOverloads
fun Activity.applyTheming(
    @StyleRes material2ThemeStyleRes: Int,
    @StyleRes material3CustomColorsThemeStyleRes: Int,
    @StyleRes material3DynamicColorsThemeStyleRes: Int,
    preferencesFacade: ThemingPreferencesFacade? = null
) {
    val preferences = preferencesFacade?.themingPreferences ?: ThemingPreferences(this)
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