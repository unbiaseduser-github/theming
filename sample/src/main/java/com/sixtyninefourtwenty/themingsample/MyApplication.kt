package com.sixtyninefourtwenty.themingsample

import android.app.Application
import android.content.Context

class MyApplication : Application() {

    val themingPreferences = ThemingPreferences()

    companion object {
        fun get(context: Context) = context.applicationContext as MyApplication
    }

}