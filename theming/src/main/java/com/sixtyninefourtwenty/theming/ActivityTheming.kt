@file:JvmName("ActivityTheming")
@file:Suppress("unused")

package com.sixtyninefourtwenty.theming

import android.app.Activity
import android.os.Build
import androidx.annotation.StyleRes
import com.sixtyninefourtwenty.theming.preferences.ImmutableThemingPreferencesSupplier

/**
 * Apply theming to an [Activity] according to settings.
 * @param material2ThemeStyleRes Resource ID of your theme that extends [R.style.AppTheme_Material2].
 * @param material3CustomColorsThemeStyleRes Resource ID of your theme that extends
 * [R.style.AppTheme_Material3_CustomColors].
 * @param material3DynamicColorsThemeStyleRes Resource ID of your theme that extends
 * [R.style.AppTheme_Material3_DynamicColors].
 * @param preferencesSupplier Preference storage to use if available. If you're using the
 * `preference-integration` extension library, see `Activity.applyThemingWithPreference` for more
 * details
 */
fun Activity.applyTheming(
    @StyleRes material2ThemeStyleRes: Int,
    @StyleRes material3CustomColorsThemeStyleRes: Int,
    @StyleRes material3DynamicColorsThemeStyleRes: Int,
    preferencesSupplier: ImmutableThemingPreferencesSupplier
) {
    val themeStyleRes: Int = if (!preferencesSupplier.md3) {
        material2ThemeStyleRes
    } else if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.R || preferencesSupplier.useM3CustomColorThemeOnAndroid12) {
        material3CustomColorsThemeStyleRes
    } else {
        material3DynamicColorsThemeStyleRes
    }
    setTheme(themeStyleRes)
    preferencesSupplier.lightDarkMode.apply()
    preferencesSupplier.themeColor.applyTo(theme)
}