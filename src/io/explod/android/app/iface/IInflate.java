package io.explod.android.app.iface;

import android.content.Context;

public interface IInflate {

	/**
	 * Return the layout ID for this activity/view/fragment. Return 0 if you do
	 * not wish to automatically inflate.
	 * 
	 * @return
	 */
	public abstract int getLayoutId();

	/**
	 * Acquire the layout resId via {@link #getLayoutId()} and inflate this
	 * activity with that layout, if the resId is non-zero. <br/>
	 * 
	 * @param context
	 *            Context doing the inflating
	 * @return Whether or not inflation occurred.
	 */
	public abstract boolean inflate(Context context);
}