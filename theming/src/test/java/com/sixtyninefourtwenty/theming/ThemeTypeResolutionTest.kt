package com.sixtyninefourtwenty.theming

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.ParameterizedRobolectricTestRunner.Parameters

@RunWith(ParameterizedRobolectricTestRunner::class)
class ThemeTypeResolutionTest(
    private val themeResId: Int,
    private val expectedType: ThemeType
) {

    companion object {
        @JvmStatic
        @Parameters
        @Suppress("unused")
        fun args(): Iterable<Array<Any>> {
            return listOf(
                arrayOf(
                    R.style.AppTheme_Material2_Base,
                    ThemeType.M2
                ),
                arrayOf(
                    R.style.AppTheme_Material3_Base,
                    ThemeType.M3
                ),
                arrayOf(
                    com.google.android.material.R.style.Theme_MaterialComponents_DayNight,
                    ThemeType.M2
                ),
                arrayOf(
                    com.google.android.material.R.style.Theme_Material3_DayNight,
                    ThemeType.M3
                )
            )
        }
    }

    @Test
    fun test() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        context.setTheme(themeResId)
        assertEquals(
            expectedType,
            ThemeType.resolveFrom(context.theme)
        )
    }

}