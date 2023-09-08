package com.sixtyninefourtwenty.theming.preferences

import android.os.Build
import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.SwitchPreferenceCompat
import com.sixtyninefourtwenty.custompreferences.PredefinedColorPickerPreference
import com.sixtyninefourtwenty.custompreferences.PreferenceFragmentCompatAccommodateCustomDialogPreferences
import com.sixtyninefourtwenty.theming.LightDarkMode
import com.sixtyninefourtwenty.theming.R

class ThemingPreferenceFragment : PreferenceFragmentCompatAccommodateCustomDialogPreferences() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        val prefs = ThemingPreferences(requireContext())
        preferenceManager.preferenceDataStore = prefs
        preferenceScreen = preferenceManager.createPreferenceScreen(requireContext()).apply {
            addPreference(SwitchPreferenceCompat(requireContext()).apply {
                key = ThemingPreferences.MD3_KEY
                title = getString(R.string.md3)
                summary = getString(R.string.md3_pref_summary)
                setDefaultValue(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
                setOnPreferenceChangeListener { _, _ ->
                    requireActivity().recreate()
                    true
                }
            })
            addPreference(ListPreference(requireContext()).apply {
                key = ThemingPreferences.LIGHT_DARK_MODE_KEY
                title = getString(R.string.theme)
                dialogTitle = getString(R.string.theme)
                entries = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) requireContext().resources.getStringArray(R.array.themes_preference_entries) else requireContext().resources.getStringArray(R.array.themes_preference_entries_p)
                entryValues = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) requireContext().resources.getStringArray(R.array.themes_preference_entry_values) else requireContext().resources.getStringArray(R.array.themes_preference_entry_values_p)
                setIcon(when (prefs.lightDarkMode) {
                    LightDarkMode.LIGHT -> R.drawable.light_mode
                    LightDarkMode.DARK -> R.drawable.dark_mode
                    LightDarkMode.BATTERY -> R.drawable.battery_saver
                    LightDarkMode.SYSTEM -> R.drawable.android
                })
                setDefaultValue(if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) "system" else "light")
                setOnPreferenceChangeListener { _, _ ->
                    requireActivity().recreate()
                    true
                }
                summaryProvider = ListPreference.SimpleSummaryProvider.getInstance()
            })
            addPreference(PredefinedColorPickerPreference(requireContext()).apply {
                key = ThemingPreferences.PRIMARY_COLOR_KEY
                title = getString(R.string.primary_color)
                setIcon(R.drawable.palette)
                setDefaultValue("#3385ff")
                setAvailableColorsArrayRes(R.array.theme_color_preference_available_colors)
                setOnPreferenceChangeListener { _, _ ->
                    requireActivity().recreate()
                    true
                }

                if (prefs.md3 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    isEnabled = false
                    summary = getString(R.string.using_android_12_dynamic_colors)
                } else {
                    summaryProvider = PredefinedColorPickerPreference.SUMMARY_PROVIDER
                }
            })
        }
    }

}