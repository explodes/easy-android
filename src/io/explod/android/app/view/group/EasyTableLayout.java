package io.explod.android.app.view.group;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TableLayout;

import io.explod.android.app.view.group.iface.IEasyViewGroup;

/**
 * TableLayout with common tasks performed during construction, like inflation, and settings fonts.
 *
 * @author evan
 */
public abstract class EasyTableLayout extends TableLayout implements IEasyViewGroup {

    public EasyTableLayout(Context context) {
        super(context);
        this.init(context);
    }

    public EasyTableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init(context);
    }

    /**
     * Called after construction.
     * Responsible for inflation.
     * If this view is not in edit mode, it will call setFonts.
     *
     * @param context Context that constructed this view.
     */
    protected void init(Context context) {
        this.inflate(context);
        final boolean shouldSetFonts = !this.isInEditMode();
        this.start(context);
        if (shouldSetFonts) {
            this.setFonts(context);
        }
    }

    /**
     * Called after inflation, which is called after construction.
     * This is called before setFonts.
     *
     * @param context Context that started this view.
     */
    protected void start(Context context) {
    }

    @Override
    public boolean inflate(Context context) {
        int id = this.getLayoutId();
        if (id != 0) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(id, this);
            return true;
        } else {
            return false;
        }
    }

}
