@file:Suppress("unused")

package com.sixtyninefourtwenty.theming

import android.content.res.Resources
import android.util.TypedValue

enum class ThemeType {

    M2, M3;

    companion object {
        @JvmStatic
        fun resolveFrom(theme: Resources.Theme): ThemeType {
            /*
            Handle M3 compatibility stubs by picking the stubbed attribute whose implementation is
            never gonna change - colorSurfaceDim in this case.
            */
            val colorSurfaceDimValue = TypedValue()
            return if (theme.resolveAttribute(
                com.google.android.material.R.attr.colorSurfaceDim,
                colorSurfaceDimValue,
                true
            )) {
                val colorSurfaceValue = TypedValue()
                theme.resolveAttribute(
                    com.google.android.material.R.attr.colorSurface,
                    colorSurfaceValue,
                    true
                )
                return if (colorSurfaceDimValue.data == colorSurfaceValue.data) {
                    /*
                    Theme extends AppTheme.Material2.Base, has M3 compatibility stubs.
                     */
                    M2
                } else {
                    M3
                }
            } else {
                /*
                Stock MaterialComponents theme.
                 */
                M2
            }
        }
    }
}

@JvmSynthetic
fun Resources.Theme.resolveType(): ThemeType = ThemeType.resolveFrom(this)