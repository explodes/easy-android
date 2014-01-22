package io.explod.android.app.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Abstract implementation of {@link IEasyActivity} <br/>
 * 
 * Includes the things that one must usually always do, like picking a layout resId, and setting fonts. <br/>
 * 
 * {@link #onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)} is specialized to automatically inflate layout resIds returned
 * by {@link #getLayoutId()} and set view fonts by calling {@link #setFonts(android.content.Context)}
 * 
 * @author evan
 * 
 */
public abstract class EasyFragment extends Fragment implements IEasyFragment {

	public EasyFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final int layoutId = this.getLayoutId();
		if (layoutId != 0) {
			final View rootView = inflater.inflate(layoutId, container, false);
			return rootView;
		} else {
			return null;
		}
	}
}
