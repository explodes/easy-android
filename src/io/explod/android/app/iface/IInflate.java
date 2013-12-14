package io.explod.android.app.iface;

import android.content.Context;
import android.os.Bundle;

public interface IInflate {

	/**
	 * Return the layout ID for this activity. Return 0 if you do not wish to
	 * automatically inflate.
	 * 
	 * @return
	 */
	public abstract int getLayoutId();

	/**
	 * Acquire the layout resId via {@link #getLayoutId()} and inflate this
	 * activity with that layout, if the resId is non-zero. <br/>
	 * 
	 * This is called automatically during {@link #onCreate(Bundle)}.
	 * 
	 * @param context
	 *            Context doing the inflating
	 * 
	 * @return Whether or not inflation occurred.
	 */
	public abstract boolean inflate(Context context);
}