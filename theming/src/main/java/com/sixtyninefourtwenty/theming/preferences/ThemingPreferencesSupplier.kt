@file:Suppress("unused")

package com.sixtyninefourtwenty.theming.preferences

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.core.content.edit
import androidx.preference.PreferenceDataStore
import com.sixtyninefourtwenty.theming.LightDarkMode
import com.sixtyninefourtwenty.theming.ThemeColor

/**
 * Represents an object that stores theming preferences. To create a default implementation,
 * call [from].
 */
interface ThemingPreferencesSupplier {
    var md3: Boolean
    var themeColor: ThemeColor
    var lightDarkMode: LightDarkMode

    companion object {

        /**
         * Creates an implementation that uses [SharedPreferences] to store values using the
         * library's internal keys.
         */
        @JvmStatic
        fun from(
            context: Context,
            preferences: SharedPreferences
        ) = object : ThemingPreferencesSupplier {
            override var md3: Boolean
                get() = preferences.getBoolean(
                    ThemingPreferences.MD3_KEY,
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
                )
                set(value) {
                    preferences.edit { putBoolean(ThemingPreferences.MD3_KEY, value) }
                }

            override var themeColor: ThemeColor
                get() = ThemeColor.entries.first { it.getColorInt(context) == preferences.getInt(
                    ThemingPreferences.PRIMARY_COLOR_KEY, ThemeColor.BLUE.getColorInt(context)
                ) }
                set(value) {
                    preferences.edit { putInt(ThemingPreferences.PRIMARY_COLOR_KEY, value.getColorInt(context)) }
                }

            override var lightDarkMode: LightDarkMode
                get() = LightDarkMode.entries.first { it.getPrefValue(context) == preferences.getString(
                    ThemingPreferences.LIGHT_DARK_MODE_KEY,
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        LightDarkMode.SYSTEM.getPrefValue(context)
                    } else {
                        LightDarkMode.LIGHT.getPrefValue(context)
                    })
                }
                set(value) {
                    preferences.edit { putString(ThemingPreferences.LIGHT_DARK_MODE_KEY, value.getPrefValue(context)) }
                }

        }

        /**
         * Creates an implementation that uses [PreferenceDataStore] to store values using the
         * library's internal keys.
         */
        @JvmStatic
        fun from(
            context: Context,
            store: PreferenceDataStore
        ) = object : ThemingPreferencesSupplier {
            override var md3: Boolean
                get() = store.getBoolean(ThemingPreferences.MD3_KEY, Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
                set(value) = store.putBoolean(ThemingPreferences.MD3_KEY, value)

            override var themeColor: ThemeColor
                get() = ThemeColor.entries.first { it.getColorInt(context) == store.getInt(
                    ThemingPreferences.PRIMARY_COLOR_KEY, ThemeColor.BLUE.getColorInt(context)) }
                set(value) = store.putInt(ThemingPreferences.PRIMARY_COLOR_KEY, value.getColorInt(context))

            override var lightDarkMode: LightDarkMode
                get() = LightDarkMode.entries.first { it.getPrefValue(context) == store.getString(
                    ThemingPreferences.LIGHT_DARK_MODE_KEY,
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        LightDarkMode.SYSTEM.getPrefValue(context)
                    } else {
                        LightDarkMode.LIGHT.getPrefValue(context)
                    })
                }
                set(value) = store.putString(ThemingPreferences.LIGHT_DARK_MODE_KEY, value.getPrefValue(context))
        }
    }
}

/**
 * Extension wrapper for [ThemingPreferencesSupplier.from].
 */
fun SharedPreferences.toThemingPreferencesSupplier(context: Context) =
    ThemingPreferencesSupplier.from(context, this)

/**
 * Extension wrapper for [ThemingPreferencesSupplier.from].
 */
fun PreferenceDataStore.toThemingPreferencesSupplier(context: Context) =
    ThemingPreferencesSupplier.from(context, this)
