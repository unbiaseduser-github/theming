package com.sixtyninefourtwenty.theming.preferences

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceGroup
import com.sixtyninefourtwenty.custompreferences.PreferenceFragmentCompatAccommodateCustomDialogPreferences

/**
 * Pre-made [PreferenceFragmentCompat] that contains theming preferences added by
 * [PreferenceGroup.addThemingPreferences]. This fragment uses an internal preferences storage.
 */
class ThemingPreferenceFragment : PreferenceFragmentCompatAccommodateCustomDialogPreferences() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        val prefs = ThemingPreferences(requireContext())
        preferenceManager.preferenceDataStore = prefs
        preferenceScreen = preferenceManager.createPreferenceScreen(requireContext()).apply {
            addM3Preference(requireActivity())
            addLightDarkModePreference(requireActivity(), prefs.lightDarkMode)
            addThemeColorPreference(requireActivity(), prefs.md3)
        }
    }

}