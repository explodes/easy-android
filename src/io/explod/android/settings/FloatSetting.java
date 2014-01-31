package io.explod.android.settings;

import android.content.SharedPreferences;

/**
 * See {@link BaseSetting} for examples.
 */
public class FloatSetting extends BaseSetting<Float> {

    /**
     * Construct this setting
     *
     * @param store        Name of the {@link android.content.SharedPreferences} store
     * @param name         Name of the setting
     * @param defaultValue Default value of the setting
     */
    public FloatSetting(String store, String name, Float defaultValue) {
        super(store, name, defaultValue);
    }

    @Override
    public Float acquire(SharedPreferences prefs, String name, Float defaultValue) {
        return prefs.getFloat(name, defaultValue);
    }

    @Override
    public void update(SharedPreferences.Editor editor, String name, Float value) {
        editor.putFloat(name, value);
    }
}
