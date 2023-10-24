package com.sixtyninefourtwenty.theming.preferences

import android.os.Bundle
import com.sixtyninefourtwenty.custompreferences.PreferenceFragmentCompatAccommodateCustomDialogPreferences

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