package io.explod.android.settings;

import android.content.SharedPreferences;

/**
 * See {@link io.explod.android.settings.BaseSetting} for examples.
 */
public class BooleanSetting extends BaseSetting<Boolean> {

    /**
     * Construct this setting
     *
     * @param store        Name of the {@link android.content.SharedPreferences} store
     * @param name         Name of the setting
     * @param defaultValue Default value of the setting
     */
    public BooleanSetting(String store, String name, boolean defaultValue) {
        super(store, name, defaultValue);
    }

    @Override
    public Boolean acquire(SharedPreferences prefs, String name, Boolean defaultValue) {
        return prefs.getBoolean(name, defaultValue);
    }

    @Override
    public void update(SharedPreferences.Editor editor, String name, Boolean value) {
        editor.putBoolean(name, value);
    }
}
