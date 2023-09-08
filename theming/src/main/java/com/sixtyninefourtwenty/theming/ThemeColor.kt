package com.sixtyninefourtwenty.theming

import android.content.Context
import android.content.res.Resources
import androidx.annotation.ColorRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat

enum class ThemeColor(@StyleRes private val themeColorStyleRes: Int, @ColorRes val colorRes: Int) {
    BLUE(R.style.ThemeColors_Blue, R.color.blue_fixed),
    RED(R.style.ThemeColors_Red, R.color.red_fixed),
    GREEN(R.style.ThemeColors_Green, R.color.green_fixed),
    PURPLE(R.style.ThemeColors_Purple, R.color.purple_fixed),
    ORANGE(R.style.ThemeColors_Orange, R.color.orange_fixed),
    PINK(R.style.ThemeColors_Pink, R.color.pink_fixed);

    fun getColorInt(context: Context) = ContextCompat.getColor(context, colorRes)

    fun applyTo(theme: Resources.Theme) {
        theme.applyStyle(themeColorStyleRes, true)
    }
}