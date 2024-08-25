package com.sixtyninefourtwenty.theming

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.util.TypedValue
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf

@RunWith(AndroidJUnit4::class)
class ThemeColorApplicationTest {

    private fun testCommon(
        activity: Activity,
        themeColor: ThemeColor,
        @ColorRes colorRes: Int
    ) {
        themeColor.applyTo(activity.theme)
        val tv = TypedValue()
        activity.theme.resolveAttribute(R.attr.m2ColorPrimary, tv, true)
        assertEquals(ContextCompat.getColor(activity, colorRes), tv.data)
    }

    @Test
    fun test() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val activityInfo = ActivityInfo().apply {
            name = AppCompatActivity::class.java.name
            packageName = context.packageName
            theme = androidx.appcompat.R.style.Theme_AppCompat
        }
        shadowOf(context.packageManager).addOrUpdateActivity(activityInfo)
        ActivityScenario.launch(AppCompatActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                ThemeColor.entries.forEach {
                    testCommon(activity, it, when (it) {
                        ThemeColor.BLUE -> R.color.m2ColorPrimary_blue
                        ThemeColor.RED -> R.color.m2ColorPrimary_red
                        ThemeColor.GREEN -> R.color.m2ColorPrimary_green
                        ThemeColor.PURPLE -> R.color.m2ColorPrimary_purple
                        ThemeColor.ORANGE -> R.color.m2ColorPrimary_orange
                        ThemeColor.PINK -> R.color.m2ColorPrimary_pink
                    })
                }
            }
        }
    }

}