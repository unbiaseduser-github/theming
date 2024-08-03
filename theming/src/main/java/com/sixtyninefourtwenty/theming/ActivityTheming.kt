@file:JvmName("ActivityTheming")
@file:Suppress("unused")

package com.sixtyninefourtwenty.theming

import android.app.Activity
import android.os.Build
import androidx.annotation.StyleRes
import com.sixtyninefourtwenty.theming.preferences.ImmutableThemingPreferencesSupplier
import com.sixtyninefourtwenty.theming.preferences.ImmutableThemingPreferencesSupplierWithoutM3CustomColor

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
    applyThemingCommon(
        preferencesSupplier.determineTheme(
            material2ThemeStyleRes,
            material3CustomColorsThemeStyleRes,
            material3DynamicColorsThemeStyleRes
        ),
        preferencesSupplier
    )
}

/**
 * Version of [applyTheming] without a Material 3 custom colors theme.
 * @param material2ThemeStyleRes Resource ID of your theme that extends [R.style.AppTheme_Material2].
 * @param material3DynamicColorsThemeStyleRes Resource ID of your theme that extends
 * [R.style.AppTheme_Material3_DynamicColors].
 * @param preferencesSupplier Preference storage to use if available. If you're using the
 * `preference-integration` extension library, see `Activity.applyThemingWithPreference` for more
 * details
 */
fun Activity.applyThemingWithoutM3CustomColors(
    @StyleRes material2ThemeStyleRes: Int,
    @StyleRes material3DynamicColorsThemeStyleRes: Int,
    preferencesSupplier: ImmutableThemingPreferencesSupplierWithoutM3CustomColor
) {
    applyThemingCommon(
        preferencesSupplier.determineThemeWithoutM3CustomColors(
            material2ThemeStyleRes,
            material3DynamicColorsThemeStyleRes
        ),
        preferencesSupplier
    )
}

private fun Activity.applyThemingCommon(
    @StyleRes themeStyleRes: Int,
    preferencesSupplier: ImmutableThemingPreferencesSupplierWithoutM3CustomColor
) {
    setTheme(themeStyleRes)
    preferencesSupplier.lightDarkMode.apply()
    preferencesSupplier.themeColor.applyTo(theme)
}
