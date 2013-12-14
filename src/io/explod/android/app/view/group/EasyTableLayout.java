package io.explod.android.app.view.group;

import io.explod.android.app.view.group.iface.IEasyViewGroup;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TableLayout;

/**
 * TableLayout with common tasks performed during construction, like inflation, and settings fonts.
 * 
 * @author evan
 * 
 */
public abstract class EasyTableLayout extends TableLayout implements IEasyViewGroup {

	public EasyTableLayout(Context context) {
		super(context);
		this.start(context);
	}

	public EasyTableLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.start(context);
	}

	protected void start(Context context) {
		this.inflate(context);
		if (!this.isInEditMode()) {
			this.setFonts(context);
		}
	}

	@Override
	public boolean inflate(Context context) {
		final int id = this.getLayoutId();
		if (id != 0) {
			final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			inflater.inflate(id, this);
			return true;
		} else {
			return false;
		}
	}

}
