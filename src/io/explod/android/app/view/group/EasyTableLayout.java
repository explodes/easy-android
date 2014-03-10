package io.explod.android.app.view.group;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TableLayout;

import io.explod.android.app.view.group.iface.IEasyViewGroup;

/**
 * TableLayout with common tasks performed during construction, like inflation,
 * and settings fonts.
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

	protected void init(Context context) {
		ViewFunctions.init(context, this);
	}

	@Override
	public boolean inflate(Context context) {
		return ViewFunctions.inflate(context, this);
	}

	@Override
	public void start(Context context) {
		// Perform your initialization code here.
	}

}
