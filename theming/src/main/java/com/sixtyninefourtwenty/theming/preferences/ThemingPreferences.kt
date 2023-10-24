package com.sixtyninefourtwenty.theming.preferences

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.core.content.edit
import androidx.preference.PreferenceDataStore
import com.sixtyninefourtwenty.theming.LightDarkMode
import com.sixtyninefourtwenty.theming.ThemeColor

internal class ThemingPreferences(
    private val context: Context,
    private val preferences: SharedPreferences = context.getSharedPreferences("theme_preferences", Context.MODE_PRIVATE)
) : PreferenceDataStore() {

    var md3: Boolean
        get() = getBoolean(MD3_KEY, Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
        set(value) = putBoolean(MD3_KEY, value)

    var themeColor: ThemeColor
        get() = ThemeColor.entries.first { it.getColorInt(context) == getInt(PRIMARY_COLOR_KEY, ThemeColor.BLUE.getColorInt(context)) }
        set(value) = putInt(PRIMARY_COLOR_KEY, value.getColorInt(context))

    var lightDarkMode: LightDarkMode
        get() = LightDarkMode.entries.first { it.getPrefValue(context) == getString(LIGHT_DARK_MODE_KEY,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                LightDarkMode.SYSTEM.getPrefValue(context)
            } else {
                LightDarkMode.LIGHT.getPrefValue(context)
            })
        }
        set(value) = putString(LIGHT_DARK_MODE_KEY, value.getPrefValue(context))

    override fun putString(key: String?, value: String?) {
        preferences.edit { putString(key, value) }
    }

    override fun putStringSet(key: String?, values: MutableSet<String>?) {
        preferences.edit { putStringSet(key, values) }
    }

    override fun putInt(key: String?, value: Int) {
        preferences.edit { putInt(key, value) }
    }

    override fun putLong(key: String?, value: Long) {
        preferences.edit { putLong(key, value) }
    }

    override fun putFloat(key: String?, value: Float) {
        preferences.edit { putFloat(key, value) }
    }

    override fun putBoolean(key: String?, value: Boolean) {
        preferences.edit { putBoolean(key, value) }
    }

    override fun getString(key: String?, defValue: String?): String? {
        return preferences.getString(key, defValue)
    }

    override fun getStringSet(key: String?, defValues: MutableSet<String>?): MutableSet<String>? {
        return preferences.getStringSet(key, defValues)
    }

    override fun getInt(key: String?, defValue: Int): Int {
        return preferences.getInt(key, defValue)
    }

    override fun getLong(key: String?, defValue: Long): Long {
        return preferences.getLong(key, defValue)
    }

    override fun getFloat(key: String?, defValue: Float): Float {
        return preferences.getFloat(key, defValue)
    }

    override fun getBoolean(key: String?, defValue: Boolean): Boolean {
        return preferences.getBoolean(key, defValue)
    }

    companion object {
        const val MD3_KEY = "md3"
        const val LIGHT_DARK_MODE_KEY = "light_dark_mode"
        const val PRIMARY_COLOR_KEY = "primary_color"
    }

}