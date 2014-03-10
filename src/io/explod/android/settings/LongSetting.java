package io.explod.android.settings;

import android.content.SharedPreferences;

/**
 * See {@link io.explod.android.settings.BaseSetting} for examples.
 */
public class LongSetting extends BaseSetting<Long> {

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
	public LongSetting(String store, String name, Long defaultValue) {
		super(store, name, defaultValue);
	}

	@Override
	public Long acquire(SharedPreferences prefs, String name, Long defaultValue) {
		return prefs.getLong(name, defaultValue);
	}

	@Override
	public void update(SharedPreferences.Editor editor, String name, Long value) {
		editor.putLong(name, value);
	}
}
