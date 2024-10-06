package com.sixtyninefourtwenty.themingsample

import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.preference.Preference
import androidx.preference.PreferenceCategory
import androidx.preference.PreferenceFragmentCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        val context = requireContext()
        val themingPreferences = MyApplication.get(context).themingPreferences
        val m3Onm2Category = PreferenceCategory(context).apply {
            title = "Material 3 components on Material 2 themes"
        }
        preferenceScreen = preferenceManager.createPreferenceScreen(context).apply {
            addPreference(Preference(context).apply {
                title = "Theme application"
                setOnPreferenceClickListener {
                    requireActivity().supportFragmentManager.commit {
                        replace<ThemeApplicationFragment>(R.id.fragment_container)
                        addToBackStack(null)
                    }
                    true
                }
            })
            addPreference(m3Onm2Category)
            with(m3Onm2Category) {
                addPreference(Preference(context).apply {
                    title = "Material Search"
                    setOnPreferenceClickListener {
                        if (themingPreferences.md3) {
                            MaterialAlertDialogBuilder(context)
                                .setMessage("Not using Material 2 theme. Set theme to Material 2 now?")
                                .setPositiveButton(android.R.string.ok) { _, _ ->
                                    themingPreferences.md3 = false
                                    requireActivity().recreate()
                                }
                                .setNegativeButton(android.R.string.cancel, null)
                                .show()
                            return@setOnPreferenceClickListener true
                        }
                        requireActivity().supportFragmentManager.commit {
                            replace<MaterialSearchFragment>(R.id.fragment_container)
                            addToBackStack(null)
                        }
                        true
                    }
                })
            }
        }
    }

}