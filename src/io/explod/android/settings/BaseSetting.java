package io.explod.android.settings;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Settings item for quick and easy settings class creation.
 * <p/>
 * example:
 * 
 * <pre>
 * public final class AppSettings {
 * 	public static final BooleanSetting useMetric = new BooleanSetting(&quot;prefs&quot;, &quot;useMetric&quot;, true);
 * 	public static final BooleanSetting autoPost = new BooleanSetting(&quot;prefs&quot;, &quot;autoPost&quot;, true);
 * }
 * </pre>
 * <p/>
 * and used like:
 * <p/>
 * 
 * <pre>
 * boolean useMetric = AppSettings.Metric.get(this);
 * </pre>
 * 
 * @param <T>
 *            Type of setting to save.
 */
public abstract class BaseSetting<T> {

	// inner

	/**
	 * Optional listener for settings changes
	 */
	public static interface ISettingListener<T> {
		/**
		 * Fired when settings change.
		 * 
		 * @param store
		 *            The name of the preferences store
		 * @param name
		 *            The name of the preference
		 * @param value
		 *            The new value
		 */
		public void settingChanged(String store, String name, T value);
	}

	// instance

	/**
	 * Preferences store to save to
	 */
	private String store;
	/**
	 * Name of the preference
	 */
	private String name;
	/**
	 * Default setting value
	 */
	private T defaultValue;
	/**
	 * Optional setting-change listener
	 */
	private ISettingListener<T> listener;

	/**
	 * Construct a new setting
	 * 
	 * @param store
	 *            The name of the preferences store
	 * @param name
	 *            The name of the preference
	 * @param defaultValue
	 *            Default setting value
	 */
	public BaseSetting(String store, String name, T defaultValue) {
		this.store = store;
		this.name = name;
		this.defaultValue = defaultValue;
	}

	/**
	 * @return Returns the name of this setting
	 */
	protected String getName() {
		return this.name;
	}

	/**
	 * @return Returns the name of the preference store
	 */
	protected String getStore() {
		return this.store;
	}

	/**
	 * @return Returns the default value
	 */
	protected T getDefaultValue() {
		return this.defaultValue;
	}

	/**
	 * Get the {@link android.content.SharedPreferences} for the given mContext
	 * with this Setting's store name.
	 * 
	 * @param context
	 *            The {@link android.content.Context} that has
	 *            {@link android.content.SharedPreferences}
	 * @return {@link android.content.SharedPreferences} in mode
	 *         {@link android.content.Context#MODE_PRIVATE}
	 */
	protected SharedPreferences getPrefs(Context context) {
		final String store = this.getStore();
		return context.getSharedPreferences(store, Context.MODE_PRIVATE);
	}

	/**
	 * Save the value to preferences
	 * 
	 * @param context
	 *            The {@link android.content.Context} that has
	 *            {@link android.content.SharedPreferences}
	 * @param value
	 *            The value to save
	 */
	public void set(Context context, T value) {
		final SharedPreferences prefs = this.getPrefs(context);
		final SharedPreferences.Editor editor = prefs.edit();
		final String name = this.getName();
		this.update(editor, name, value);
		editor.apply();
		if (this.listener != null) {
			final String store = this.getStore();
			this.listener.settingChanged(store, name, value);
		}
	}

	/**
	 * Get the setting from preferences
	 * 
	 * @param context
	 *            The {@link android.content.Context} that has
	 *            {@link android.content.SharedPreferences}
	 * @return The setting in preferences or its default value
	 */
	public T get(Context context) {
		final SharedPreferences prefs = this.getPrefs(context);
		final T defaultValue = this.getDefaultValue();
		if (prefs != null) {
			final String name = this.getName();
			return this.acquire(prefs, name, defaultValue);
		} else {
			return defaultValue;
		}
	}

	public void setSettingListener(ISettingListener<T> listener) {
		this.listener = listener;
	}

	/**
	 * Perform the actual updating. Do not
	 * {@link android.content.SharedPreferences.Editor#commit()} or
	 * {@link android.content.SharedPreferences.Editor#apply()}
	 * 
	 * @param editor
	 *            The {@link android.content.SharedPreferences.Editor} to save
	 *            the value to
	 * @param name
	 *            Name of the setting to update
	 * @param value
	 *            The value to save
	 */
	protected abstract void update(SharedPreferences.Editor editor, String name, T value);

	/**
	 * Actually pull the value from prefrences
	 * 
	 * @param prefs
	 *            {@link android.content.SharedPreferences} to pull from
	 * @param name
	 *            Name of the setting to acquire
	 * @param defaultValue
	 *            The default value to return
	 * @return The value from {@link android.content.SharedPreferences} or the
	 *         default value
	 */
	protected abstract T acquire(SharedPreferences prefs, String name, T defaultValue);

}
