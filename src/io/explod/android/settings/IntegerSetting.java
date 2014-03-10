package io.explod.android.settings;

import android.content.SharedPreferences;

/**
 * See {@link io.explod.android.settings.BaseSetting} for examples.
 */
public class IntegerSetting extends BaseSetting<Integer> {

	/**
	 * Construct this setting
	 * 
	 * @param store
	 *            Name of the {@link android.content.SharedPreferences} store
	 * @param name
	 *            Name of the setting
	 * @param defaultValue
	 *            Default value of the setting
	 */
	public IntegerSetting(String store, String name, Integer defaultValue) {
		super(store, name, defaultValue);
	}

	@Override
	public Integer acquire(SharedPreferences prefs, String name, Integer defaultValue) {
		return prefs.getInt(name, defaultValue);
	}

	@Override
	public void update(SharedPreferences.Editor editor, String name, Integer value) {
		editor.putInt(name, value);
	}
}
