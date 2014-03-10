package io.explod.android.app.activity;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Abstract implementation of {@link IEasyActivity} <br/>
 * <p/>
 * Includes the things that one must usually always do, like picking a layout
 * resId, and setting fonts. <br/>
 * <p/>
 * {@link #onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)}
 * is specialized to automatically inflate layout resIds returned by
 * {@link #getLayoutId()}.
 * <p/>
 * if a rootView was created:
 * <p/>
 * Then, {@link #onRootViewCreated(android.view.View)} is called.
 * <p/>
 * Finally, {@link #setFonts(android.content.Context)} is called.
 * 
 * @author evan
 */
public abstract class EasyFragment extends Fragment implements IEasyFragment {

	/**
	 * Variable for the root view, if it has been created.
	 */
	private View rootView;

	/**
	 * Default constructor required.
	 */
	public EasyFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final int layoutId = this.getLayoutId();
		if (layoutId != 0) {
			if (this.rootView == null || this.shouldAlwaysCreateRootView()) {
				this.rootView = inflater.inflate(layoutId, container, false);
				if (this.rootView != null) {
					final Context context = this.getActivity();
					this.onRootViewCreated(this.rootView);
					this.setFonts(context);
				}
			}
			return rootView;
		} else {
			return null;
		}
	}

	@Override
	public View getRootView() {
		return this.rootView;
	}

	/**
	 * @return Return whether or not
	 *         {@link #onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)}
	 *         should be called even after the rootView has been created.
	 */
	private boolean shouldAlwaysCreateRootView() {
		return false;
	}

}
