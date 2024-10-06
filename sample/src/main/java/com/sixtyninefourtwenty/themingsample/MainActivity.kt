package com.sixtyninefourtwenty.themingsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.sixtyninefourtwenty.theming.applyTheming
import com.sixtyninefourtwenty.themingsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val themingPreferences by lazy { MyApplication.get(this).themingPreferences }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        themingPreferences.onRestoreInstanceState(savedInstanceState)
        applyTheming(
            material2ThemeStyleRes = com.sixtyninefourtwenty.theming.R.style.AppTheme_Material2,
            material3CustomColorsThemeStyleRes = com.sixtyninefourtwenty.theming.R.style.AppTheme_Material3_CustomColors,
            material3DynamicColorsThemeStyleRes = com.sixtyninefourtwenty.theming.R.style.AppTheme_Material3_DynamicColors,
            preferencesSupplier = themingPreferences
        )
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add<MainFragment>(R.id.fragment_container)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        themingPreferences.onSaveInstanceState(outState)
    }

}