package io.explod.android.app.view.group;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import io.explod.android.app.view.group.iface.IEasyViewGroup;

/**
 * RelativeLayout with common tasks performed during construction, like
 * inflation, and settings fonts.
 * 
 * @author evan
 */
public abstract class EasyRelativeLayout extends RelativeLayout implements IEasyViewGroup {

	public EasyRelativeLayout(Context context) {
		super(context);
		this.init(context);
	}

	public EasyRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.init(context);
	}

	public EasyRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
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
