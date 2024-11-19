## 2.4.0
- Add Material 2 `SearchBar` theme
- Add method to determine Material version of theme
- Add ability to use Material 3 components in library Material 2 themes
- Tweak spinner item layout

## 2.3.3
- Add Material 3 spinner theme
- Remove unused alert dialog theme

## 2.3.2
- Regenerate colors using fixed `theming-helper` app
- Remove `statusBarColor` on material 2 theme

## 2.3.1
- Add `<item name="windowActionModeOverlay">true</item>` to allow using stock Android action modes
- Material 3 custom colors mini-overhaul:
  - Fill in all Material 3 attributes in custom colors theme
  - Colors formerly "primary" are now seed colors for the new one

## 2.3.0
Move theme resource determiner logic to `ThemingPreferenceSupplier`

## 2.2.0
Add support for another use case: only have Material 2 theme use custom colors, Material 3 theme uses
dynamic colors on all Android versions

## 2.1.0
- Move `theming-preference-integration` module to separate repository
- Add `ImmutableThemingPreferencesSupplier` - basically acts as a guarantee that the library doesn't
  mutate `ThemingPreferencesSupplier`'s properties

## 2.0.1
Bump `custom-preferences` to 2.0

## 2.0
- Change project license from MIT to Apache 2.0
- Move preference UI stuff to separate extension module

## 1.1.5
Material 3 preference UI layout changes:
- No longer wrapped in a card view (The Settings screens in some AOSP ROMs don't have the preference
  layout rounded and don't have margins)
- Tweak preference title text appearance

## 1.1.4
Add parameters to the "add individual preference controls" methods to make it easier for users to
use custom preference values

## 1.1.3
Hide "use m3 custom colors theme on android 12" preference on android 11- for obvious reasons

## 1.1.2
Change `ThemingPreferenceFragment` to extend regular `PreferenceFragmentCompat` instead of
[custom-preferences](https://gitlab.com/unbiaseduser/custom-preferences)' `PreferenceFragmentCompatAccommodateCustomDialogPreferences`

**Retrospective note:** This is due to `custom-preferences` not being `api` scoped - it *really*
should've been, since the library uses `PredefinedColorPickerPreference` which is a dialog preference
that needs handling.

## 1.1.1
Change "use m3 custom colors theme on android 12" preference title

## 1.1
- Add ability to add individual preference controls
- Introduce `ThemingPreferenceSupplier` as an abstraction for preference storage
- Add ability to use M3 custom colors theme on Android 12+

## 1.0
Initial release