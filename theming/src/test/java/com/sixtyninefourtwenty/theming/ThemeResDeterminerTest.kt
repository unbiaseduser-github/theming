package com.sixtyninefourtwenty.theming

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sixtyninefourtwenty.theming.preferences.ImmutableThemingPreferencesSupplier
import com.sixtyninefourtwenty.theming.preferences.ImmutableThemingPreferencesSupplierWithoutM3CustomColor
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
class ThemeResDeterminerTest {

    private fun testCommon(
        obj: ImmutableThemingPreferencesSupplier,
        expected: Int
    ) {
        assertEquals(expected, obj.determineTheme(
            1, 2, 3
        ))
    }

    @Test
    @Config(maxSdk = Build.VERSION_CODES.R)
    fun testOnAndroid11AndLower() {
        testCommon(Impl(
            md3 = true,
            useM3CustomColorThemeOnAndroid12 = true
        ), 2)
        testCommon(Impl(
            md3 = true,
            useM3CustomColorThemeOnAndroid12 = false
        ), 2)
        testCommon(Impl(
            md3 = false,
            useM3CustomColorThemeOnAndroid12 = false
        ), 1)
        testCommon(Impl(
            md3 = false,
            useM3CustomColorThemeOnAndroid12 = true
        ), 1)
    }

    @Test
    @Config(minSdk = Build.VERSION_CODES.S)
    fun testOnAndroid12AndHigher() {
        testCommon(Impl(
            md3 = true,
            useM3CustomColorThemeOnAndroid12 = true
        ), 2)
        testCommon(Impl(
            md3 = true,
            useM3CustomColorThemeOnAndroid12 = false
        ), 3)
        testCommon(Impl(
            md3 = false,
            useM3CustomColorThemeOnAndroid12 = false
        ), 1)
        testCommon(Impl(
            md3 = false,
            useM3CustomColorThemeOnAndroid12 = true
        ), 1)
    }

    private fun testCommonWithoutM3CustomColor(
        obj: ImmutableThemingPreferencesSupplierWithoutM3CustomColor,
        expected: Int
    ) {
        assertEquals(expected, obj.determineThemeWithoutM3CustomColors(
            1, 2
        ))
    }

    @Test
    fun testWithoutM3CustomColor() {
        testCommonWithoutM3CustomColor(ImplWithoutM3CustomColor(
            md3 = true
        ), 2)
        testCommonWithoutM3CustomColor(ImplWithoutM3CustomColor(
            md3 = false
        ), 1)
    }

}

private class Impl(
    override val md3: Boolean,
    override val useM3CustomColorThemeOnAndroid12: Boolean,
    override val themeColor: ThemeColor = ThemeColor.BLUE,
    override val lightDarkMode: LightDarkMode = LightDarkMode.SYSTEM
) : ImmutableThemingPreferencesSupplier

private class ImplWithoutM3CustomColor(
    override val md3: Boolean,
    override val themeColor: ThemeColor = ThemeColor.BLUE,
    override val lightDarkMode: LightDarkMode = LightDarkMode.SYSTEM
) : ImmutableThemingPreferencesSupplierWithoutM3CustomColor