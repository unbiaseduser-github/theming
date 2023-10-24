package com.sixtyninefourtwenty.theming

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.sixtyninefourtwenty.theming.preferences.ThemingPreferences

@Suppress("unused", "MemberVisibilityCanBePrivate")
class ThemingPreferencesFacade @JvmOverloads constructor(
    context: Context,
    preferences: SharedPreferences? = null
) {

    @JvmSynthetic
    internal val themingPreferences: ThemingPreferences

    init {
        themingPreferences = if (preferences != null) {
            ThemingPreferences(context, preferences)
        } else {
            ThemingPreferences(context)
        }
    }

    var md3: Boolean by themingPreferences::md3

    var themeColor: ThemeColor by themingPreferences::themeColor

    var lightDarkMode: LightDarkMode by themingPreferences::lightDarkMode

    private fun doStuffAndRecreate(activity: Activity, block: (Activity) -> Unit) {
        block(activity)
        activity.recreate()
    }

    fun setMd3SettingAndRecreate(activity: Activity, md3: Boolean) = doStuffAndRecreate(activity) {
        this.md3 = md3
    }

    fun setThemeColorSettingAndRecreate(activity: Activity, themeColor: ThemeColor) = doStuffAndRecreate(activity) {
        this.themeColor = themeColor
    }

    fun setLightDarkModeSettingAndRecreate(activity: Activity, lightDarkMode: LightDarkMode) = doStuffAndRecreate(activity) {
        this.lightDarkMode = lightDarkMode
    }

}