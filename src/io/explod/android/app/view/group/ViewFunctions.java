package io.explod.android.app.view.group;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.explod.android.app.view.group.iface.IEasyViewGroup;

/**
 * Created by evan on 3/4/14.
 */
public final class ViewFunctions {

    /**
     * Called after construction.
     * Responsible for inflation, if applicable.
     * If this view is not in edit mode, it will call setFonts.
     *
     * @param context Context that constructed this view.
     * @param view    View to initialize
     */
    public static final void init(Context context, IEasyViewGroup view) {
        view.inflate(context);
        final boolean shouldSetFonts = !((View) view).isInEditMode();
        view.start(context);
        if (shouldSetFonts) {
            view.setFonts(context);
        }
    }

    /**
     * inflate an inflatable EasyViewGroup
     *
     * @param context Context from which to inflate
     * @param view    View to inflate
     * @return Whether or not inflation occurred
     */
    public static final boolean inflate(Context context, IEasyViewGroup view) {
        final int id = view.getLayoutId();
        if (id != 0) {
            final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(id, (ViewGroup) view);
            return true;
        } else {
            return false;
        }
    }
}
