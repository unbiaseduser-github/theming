package com.sixtyninefourtwenty.theming

import android.content.res.Resources
import androidx.annotation.StyleRes

enum class ThemeColor(@StyleRes private val themeColorStyleRes: Int) {
    BLUE(R.style.ThemeColors_Blue),
    RED(R.style.ThemeColors_Red),
    GREEN(R.style.ThemeColors_Green),
    PURPLE(R.style.ThemeColors_Purple),
    ORANGE(R.style.ThemeColors_Orange),
    PINK(R.style.ThemeColors_Pink);

    fun applyTo(theme: Resources.Theme) {
        theme.applyStyle(themeColorStyleRes, true)
    }
}