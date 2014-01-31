package io.explod.android.app.activity;

import io.explod.android.app.iface.ISetFonts;

/**
 * Interface for {@link EasyFragment} <br/>
 * Includes the things that one must usually always do, like picking a layout resId, and setting fonts.
 *
 * @author evan
 */
public interface IEasyFragment extends ISetFonts {

    /**
     * Return the layout ID for this fragment. Return 0 if you do not wish to
     * automatically inflate.
     *
     * @return
     */
    public abstract int getLayoutId();
}
