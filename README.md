[![jitpack badge](https://jitpack.io/v/unbiaseduser-github/theming.svg)](https://jitpack.io/#unbiaseduser-github/theming)

Library that aims to make implementing Material 2 *and* Material 3 themes into an Android app as painless as possible.
Also contains common theme configurations for all my apps - change it to your liking!

It consists of 2 parts:

- Core: The base of the lib, hosted in this repository. Consists of model classes and the core 
"apply theme to activity" logic.
- Preference + custom preferences: Integration with the AndroidX Preference library and the
[custom-preferences](https://gitlab.com/unbiaseduser/custom-preferences) library. Allows adding
preference UI that controls aspects of the theming preferences and provides Material 3 themes for
the preference UI of both libraries. Hosted in [this repository](https://gitlab.com/unbiaseduser/custom-preferences-theming-integration)

These use cases are supported:
- Material 2 with custom colors + Material 3 with dynamic colors
- Material 2 with custom colors + Material 3 with dynamic colors + Material 3 with custom colors

Material 2 with *fixed* colors is not supported, since
- M2 with fixed colors + M3 with dynamic colors is trivial enough to implement that you don't need a library to do that.
- If you offer M3 with custom colors, you also want to offer M2 with custom colors, otherwise it would just be inconsistent.

Material 3 with *only* custom colors is not supported, since one of its main selling points is the dynamic colors system. It would be strange to offer M3 without that.

# What's the point?
## The facts
Android is objectively a *very* fragmented platform. Many people simply can't or don't want to replace
their already working devices - the most glaring consequence of that is they're stuck with old versions
of Android which don't use the latest Material Design.

## Personal rant
As of August 5 2024, many apps have been migrated to Material 3. That's cool and all, but all developers
seem to forget that old devices exist. While most users might prefer M3 apps on a M2 system, others
might not, and to them, it's like a slap in the face. It's like telling them
"Oh, you want my app to have the same look and feel as your device? Just buy a new one lol".
And that simply doesn't sit right with me.

## The goal
This library allows app developers to make their apps feel "at home" with older devices by using Material 2 theme,
while still letting users use the latest Material 3 theme if they desire.

# Colors
Each custom color scheme has a light mode and a dark mode variant.
- Material 2: The color listed on the left is the one used in light mode, the one on the right for the dark mode.
- Material 3: The "seed" color is used to generate light and dark mode colors on [Google's theme generator]
(https://material-foundation.github.io/material-theme-builder/). The generated files are punched into
[the helper app](https://gitlab.com/unbiaseduser/theming-helper).

The following custom color schemes are supported:

- Material 2
  + Blue (primary color: #3385ff/#64b4ff)
  + Red (primary color: #d8260e/#ff6141)
  + Green (primary color: #00c71e/#73da6f)
  + Purple (primary color: #7700f6/#a66afb)
  + Orange (primary color: #ff8800/#ffa424)
  + Pink (primary color: #e700ef/#f390f6)

- Material 3
  + Blue (seed color: #005bc0)
  + Red (seed color: #bc1400)
  + Green (seed color: #006e0b)
  + Purple (seed color: #7a0df9)
  + Orange (seed color: #924c00)
  + Pink (seed color: #a700ad)

For Material 2, all other colors in a scheme (secondary colors, etc.) are derived from the primary
colors mentioned above.

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
        val preferences: ImmutableThemingPreferencesSupplier = getMyPreferencesSupplier()
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
                this,
                R.style.MyAppTheme_Material2,
                R.style.MyAppTheme_Material3_CustomColors,
                R.style.MyAppTheme_Material3_DynamicColors,
                preferences
        );
    }
    
}
```

`ImmutableThemingPreferencesSupplier`s and `ImmutableThemingPreferencesSupplierWithoutM3CustomColor`s
(or specifically their mutable counterparts, `ThemingPreferencesSupplier` and `ThemingPreferencesSupplierWithoutM3CustomColor`)
can be created from `SharedPreferences` and `PreferenceDataStore` using the preference integration library mentioned above.

Of course, you can create custom implementations of those too.

# Extras
- Custom attributes that map to different styles depending on whether the theme currently used is M2 or M3:
  + themeDependantTitleStyle
  + themeDependantFilterChipStyle
  + themeDependantInputChipStyle
  + themeDependantSuggestionChipStyle
  + themeDependantAssistChipStyle
- "Fallback" themes to help with [fragment testing](https://developer.android.com/guide/fragments/test)
in case any of the above attributes are used, as said themes allow to avoid having to redefine them.

For example:

Your theme:
```xml
<style name="MyAppTheme.Material2" parent="AppTheme.Material2">
    <item name="yourCustomAttribute">foo</item>
</style>
```

Your layout references `yourCustomAttribute` and one or more library attributes, in this case `themeDependantTitleStyle`.

When it comes to fragment testing, your test theme instead of
```xml
<style name="MyAppTheme.Material2.Test" parent="Theme.MaterialComponents.DayNight.NoActionBar">
    <item name="yourCustomAttribute">foo</item>
    <item name="themeDependantTitleStyle">@style/TextAppearance.AppCompat.Title</item>
</style>
```

can simply be
```xml
<style name="MyAppTheme.Material2.Test" parent="AppTheme.Material2.Fallback">
    <item name="yourCustomAttribute">foo</item>
</style>
```

# Interesting tidbits
One way to implement Material 3 custom colors theme is by calling:
```kotlin
val yourSeedColor: Int = getSeedColorInt()
DynamicColors.applyToActivityIfAvailable(
  activity,
  DynamicColorsOptions.Builder()
    .setContentBasedSource(yourSeedColor)
    .build()
)
```
The reason why this library doesn't use that method is because it indiscriminately applies colors
regardless of whether the user wants to use Material 3 or Material 2 theme, and there's no way to
"revert" this.

Which means that, yes, you *can* have a Material 2 theme with dynamic colors. You're welcome!

# Credits
Thanks to [Material Files](https://github.com/zhanghai/MaterialFiles) for inspiration.