package com.sixtyninefourtwenty.theming

import android.app.Activity
import android.content.Context
import com.sixtyninefourtwenty.theming.preferences.ThemingPreferences

@Suppress("unused", "MemberVisibilityCanBePrivate")
class ThemingPreferencesFacade(context: Context) {

    private val preferences = ThemingPreferences(context)

    var md3: Boolean by preferences::md3

    var themeColor: ThemeColor by preferences::themeColor

    var lightDarkMode: LightDarkMode by preferences::lightDarkMode

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