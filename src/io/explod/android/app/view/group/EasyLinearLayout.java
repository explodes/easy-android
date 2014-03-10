package io.explod.android.app.view.group;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import io.explod.android.app.view.group.iface.IEasyViewGroup;

/**
 * LinearLayout with common tasks performed during construction, like inflation,
 * and settings fonts.
 * 
 * @author evan
 */
public abstract class EasyLinearLayout extends LinearLayout implements IEasyViewGroup {

	public EasyLinearLayout(Context context) {
		super(context);
		this.init(context);
	}

	public EasyLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.init(context);
	}

	public EasyLinearLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
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
