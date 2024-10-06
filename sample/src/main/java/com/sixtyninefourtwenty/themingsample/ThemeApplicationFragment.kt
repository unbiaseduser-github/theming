package com.sixtyninefourtwenty.themingsample

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sixtyninefourtwenty.theming.LightDarkMode
import com.sixtyninefourtwenty.theming.ThemeColor
import com.sixtyninefourtwenty.themingsample.databinding.FragmentThemeApplicationBinding

class ThemeApplicationFragment : Fragment() {

    private var _binding: FragmentThemeApplicationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThemeApplicationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val themingPreferences = MyApplication.get(requireContext()).themingPreferences
        with(binding) {
            md3Card.setOnClickListener {
                md3Cb.toggle()
            }
            with(md3Cb) {
                isChecked = themingPreferences.md3
                setOnCheckedChangeListener { _, isChecked ->
                    themingPreferences.md3 = isChecked
                    requireActivity().recreate()
                }
            }
            themeCard.setOnClickListener {
                fun determineChoice(lightDarkMode: LightDarkMode): Int {
                    return when (lightDarkMode) {
                        LightDarkMode.LIGHT -> 0
                        LightDarkMode.DARK -> 1
                        LightDarkMode.BATTERY -> 2
                        LightDarkMode.SYSTEM -> 3
                    }
                }

                var choice = determineChoice(themingPreferences.lightDarkMode)
                MaterialAlertDialogBuilder(requireContext())
                    .setSingleChoiceItems(arrayOf(
                        getString(R.string.light),
                        getString(R.string.dark),
                        getString(R.string.battery_saver),
                        getString(R.string.system)
                    ), determineChoice(themingPreferences.lightDarkMode)) { _, which ->
                        choice = which
                    }
                    .setNegativeButton(android.R.string.cancel, null)
                    .setPositiveButton(android.R.string.ok) { _, _ ->
                        themingPreferences.lightDarkMode = when (choice) {
                            0 -> LightDarkMode.LIGHT
                            1 -> LightDarkMode.DARK
                            2 -> LightDarkMode.BATTERY
                            3 -> LightDarkMode.SYSTEM
                            else -> error("Unknown choice: $choice")
                        }
                        requireActivity().recreate()
                    }
                    .show()
            }
            themeIcon.setImageResource(when (themingPreferences.lightDarkMode) {
                LightDarkMode.LIGHT -> R.drawable.light_mode
                LightDarkMode.DARK -> R.drawable.dark_mode
                LightDarkMode.SYSTEM -> R.drawable.android
                LightDarkMode.BATTERY -> R.drawable.battery_saver
            })
            with(md3CustomColorsA12Card) {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.R || !themingPreferences.md3) {
                    isVisible = false
                }
                setOnClickListener {
                    md3CustomColorsA12Cb.toggle()
                }
            }
            with(md3CustomColorsA12Cb) {
                isChecked = themingPreferences.useM3CustomColorThemeOnAndroid12
                setOnCheckedChangeListener { _, isChecked ->
                    themingPreferences.useM3CustomColorThemeOnAndroid12 = isChecked
                    requireActivity().recreate()
                }
            }
            with(themeColorCard) {
                if (!themingPreferences.useM3CustomColorThemeOnAndroid12 && themingPreferences.md3 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    isVisible = false
                }
                setOnClickListener {
                    fun determineChoice(themeColor: ThemeColor): Int {
                        return when (themeColor) {
                            ThemeColor.BLUE -> 0
                            ThemeColor.RED -> 1
                            ThemeColor.GREEN -> 2
                            ThemeColor.PURPLE -> 3
                            ThemeColor.ORANGE -> 4
                            ThemeColor.PINK -> 5
                        }
                    }

                    var choice = determineChoice(themingPreferences.themeColor)
                    MaterialAlertDialogBuilder(requireContext())
                        .setSingleChoiceItems(arrayOf(
                            getString(R.string.blue),
                            getString(R.string.red),
                            getString(R.string.green),
                            getString(R.string.purple),
                            getString(R.string.orange),
                            getString(R.string.pink)
                        ), determineChoice(themingPreferences.themeColor)) { _, which ->
                            choice = which
                        }
                        .setNegativeButton(android.R.string.cancel, null)
                        .setPositiveButton(android.R.string.ok) { _, _ ->
                            themingPreferences.themeColor = when (choice) {
                                0 -> ThemeColor.BLUE
                                1 -> ThemeColor.RED
                                2 -> ThemeColor.GREEN
                                3 -> ThemeColor.PURPLE
                                4 -> ThemeColor.ORANGE
                                5 -> ThemeColor.PINK
                                else -> error("Unknown choice: $choice")
                            }
                            requireActivity().recreate()
                        }
                        .show()
                }
            }
            themeColorIcon.setBackgroundColor(when (themingPreferences.themeColor) {
                ThemeColor.BLUE -> "#3385ff".toColorInt()
                ThemeColor.RED -> "#d8260e".toColorInt()
                ThemeColor.GREEN -> "#00c71e".toColorInt()
                ThemeColor.PURPLE -> "#7700f6".toColorInt()
                ThemeColor.ORANGE -> "#ff8800".toColorInt()
                ThemeColor.PINK -> "#e700ef".toColorInt()
            })
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}