This library aims to make implementing Material 2 and Material 3 themes into an Android app as painless as possible.

These use cases are supported:
- Material 2 with custom colors + Material 3 with dynamic colors
- Material 2 with custom colors + Material 3 with dynamic colors + Material 3 with custom colors

Material 2 with *fixed* colors is not supported, since
- M2 with fixed colors + M3 with dynamic colors is trivial enough to implement that you don't need a library to do that.
- If you offer M3 with custom colors, you also want to offer M2 with custom colors, otherwise it would just be inconsistent.

Material 3 with *only* custom colors is not supported, since one of its main selling points is the dynamic colors system. It would be strange to offer M3 without that.

# Usage
Create a definition for each theme that inherits the library's:
```xml
<style name="MyAppTheme.Material2" parent="AppTheme.Material2">
    <!-- Your attributes here -->
</style>
```
```xml
<style name="MyAppTheme.Material3.DynamicColors" parent="AppTheme.Material3.DynamicColors">
    <!-- Your attributes here -->
</style>
```
```xml
<style name="MyAppTheme.Material3.CustomColors" parent="AppTheme.Material3.CustomColors">
    <!-- Your attributes here -->
</style>
```

In your `Activity` subclass, call `applyTheming` or `applyThemingWithoutM3CustomColors` in `onCreate`:
```kotlin
class MyActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        val preferences: ImmutableThemingPreferencesSupplier
        applyTheming(
            material2ThemeStyleRes = R.style.MyAppTheme_Material2,
            material3CustomColorsThemeStyleRes = R.style.MyAppTheme_Material3_CustomColors,
            material3DynamicColorsThemeStyleRes = R.style.MyAppTheme_Material3_DynamicColors,
            preferencesSupplier = preferences
        )
    }
    
}
```
```java
public class MyActivity extends AppCompatActivity {
    
    public void onCreate(Bundle savedInstanceState) {
        ImmutableThemingPreferencesSupplier preferences;
        ActivityTheming.applyTheming(
                R.style.MyAppTheme_Material2,
                R.style.MyAppTheme_Material3_CustomColors,
                R.style.MyAppTheme_Material3_DynamicColors,
                preferences
        );
    }
    
}
```

`ImmutableThemingPreferencesSupplier`s and `ImmutableThemingPreferencesSupplierWithoutM3CustomColor`s (or specifically their mutable counterparts, `ThemingPreferencesSupplier` and `ThemingPreferencesSupplierWithoutM3CustomColor`) can be created from `SharedPreferences` and `PreferenceDataStore` using [the preference integration library](https://gitlab.com/unbiaseduser/library-integrations/-/tree/master/theming-preference-integration?ref_type=heads).
Of course, you can create custom implementations of those too.