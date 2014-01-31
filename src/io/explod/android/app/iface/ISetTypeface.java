package io.explod.android.app.iface;

import android.graphics.Typeface;

/**
 * Interface for {@link io.explod.android.app.fonts.Fonts} to set custom view fonts.
 *
 * @author evan
 */
public interface ISetTypeface {

    /**
     * Apply the given typeface to this view
     *
     * @param typeface Typeface to apply to this view
     */
    public void setTypeface(Typeface typeface);

}
