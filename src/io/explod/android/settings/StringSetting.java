package io.explod.android.settings;

import android.content.SharedPreferences;

/**
 * See {@link BaseSetting} for examples.
 */
public class StringSetting extends BaseSetting<String> {

    /**
     * Construct this setting
     *
     * @param store        Name of the {@link android.content.SharedPreferences} store
     * @param name         Name of the setting
     * @param defaultValue Default value of the setting
     */
    public StringSetting(String store, String name, String defaultValue) {
        super(store, name, defaultValue);
    }

    @Override
    public String acquire(SharedPreferences prefs, String name, String defaultValue) {
        return prefs.getString(name, defaultValue);
    }

    @Override
    public void update(SharedPreferences.Editor editor, String name, String value) {
        editor.putString(name, value);
    }
}
