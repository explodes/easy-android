package io.explod.android.app.activity;

import android.view.View;

import io.explod.android.app.iface.ISetFonts;

/**
 * Interface for {@link EasyFragment} <br/>
 * Includes the things that one must usually always do, like picking a layout
 * resId, and setting fonts.
 * 
 * @author evan
 */
public interface IEasyFragment extends ISetFonts {

	/**
	 * @return Return the layout ID for this fragment. Return 0 if you do not
	 *         wish to automatically inflate.
	 */
	public abstract int getLayoutId();

	/**
	 * @return Return the root view of this fragment, if one has been created.
	 */
	public abstract View getRootView();

	/**
	 * Called when the root view has been created.
	 * 
	 * @param rootView
	 */
	public abstract void onRootViewCreated(View rootView);
}
