@file:JvmName("ThemingPreferenceUI")
@file:Suppress("unused")

package com.sixtyninefourtwenty.theming.preferences

import android.app.Activity
import android.os.Build
import androidx.preference.ListPreference
import androidx.preference.PreferenceGroup
import androidx.preference.SwitchPreferenceCompat
import com.sixtyninefourtwenty.custompreferences.PredefinedColorPickerPreference
import com.sixtyninefourtwenty.theming.LightDarkMode
import com.sixtyninefourtwenty.theming.R

fun PreferenceGroup.addThemingPreferences(activity: Activity, lightDarkMode: LightDarkMode, md3: Boolean) {
    addM3Preference(activity)
    addLightDarkModePreference(activity, lightDarkMode)
    addThemeColorPreference(activity, md3)
}

fun PreferenceGroup.addM3Preference(activity: Activity) {
    addPreference(SwitchPreferenceCompat(activity).apply {
        key = ThemingPreferences.MD3_KEY
        title = activity.getString(R.string.md3)
        summary = activity.getString(R.string.md3_pref_summary)
        setDefaultValue(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
        setOnPreferenceChangeListener { _, _ ->
            activity.recreate()
            true
        }
    })
}

fun PreferenceGroup.addLightDarkModePreference(activity: Activity, lightDarkMode: LightDarkMode) {
    addPreference(ListPreference(activity).apply {
        key = ThemingPreferences.LIGHT_DARK_MODE_KEY
        title = activity.getString(R.string.theme)
        dialogTitle = activity.getString(R.string.theme)
        entries = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            activity.resources.getStringArray(R.array.themes_preference_entries)
        } else {
            activity.resources.getStringArray(R.array.themes_preference_entries_p)
        }
        entryValues = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            activity.resources.getStringArray(R.array.themes_preference_entry_values)
        } else {
            activity.resources.getStringArray(R.array.themes_preference_entry_values_p)
        }
        setIcon(when (lightDarkMode) {
            LightDarkMode.LIGHT -> R.drawable.light_mode
            LightDarkMode.DARK -> R.drawable.dark_mode
            LightDarkMode.BATTERY -> R.drawable.battery_saver
            LightDarkMode.SYSTEM -> R.drawable.android
        })
        setDefaultValue(if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) "system" else "light")
        setOnPreferenceChangeListener { _, _ ->
            activity.recreate()
            true
        }
        summaryProvider = ListPreference.SimpleSummaryProvider.getInstance()
    })
}

fun PreferenceGroup.addThemeColorPreference(activity: Activity, md3: Boolean) {
    addPreference(PredefinedColorPickerPreference(activity).apply {
        key = ThemingPreferences.PRIMARY_COLOR_KEY
        title = activity.getString(R.string.primary_color)
        setIcon(R.drawable.palette)
        setDefaultValue("#3385ff")
        setAvailableColorsArrayRes(R.array.theme_color_preference_available_colors)
        setOnPreferenceChangeListener { _, _ ->
            activity.recreate()
            true
        }

        if (md3 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            isEnabled = false
            summary = activity.getString(R.string.using_android_12_dynamic_colors)
        } else {
            summaryProvider = PredefinedColorPickerPreference.getSimpleSummaryProvider()
        }
    })
}
