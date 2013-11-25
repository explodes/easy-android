package io.explod.easyandroid.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public abstract class EasyRelativeLayout extends RelativeLayout implements IEasyView {


	public EasyRelativeLayout(Context context) {
		super(context);
		this.start(context);
	}

	public EasyRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.start(context);
	}

	public EasyRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.start(context);
	}

	protected void start(Context context) {
		this.inflate(context);
		if (!this.isInEditMode()) {
			this.setFonts(context);
		}
	}

	protected boolean inflate(Context context) {
		final int id = this.getLayoutId();
		if (id != 0) {
			final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			inflater.inflate(id, this);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public TextView findTextViewById(int id) {
		return (TextView) this.findViewById(id);
	}

	@Override
	public Button findButtonById(int id) {
		return (Button) this.findViewById(id);
	}

	@Override
	public EditText findEditTextById(int id) {
		return (EditText) this.findViewById(id);
	}

	@Override
	public ImageView findImageViewById(int id) {
		return (ImageView) this.findViewById(id);
	}

	@Override
	public ImageButton findImageButtonById(int id) {
		return (ImageButton) this.findViewById(id);
	}

	@Override
	public GridView findGridViewById(int id) {
		GridView grid = (GridView) this.findViewById(id);
		return grid;
	}

	@Override
	public ListView findListViewById(int id) {
		ListView list = (ListView) this.findViewById(id);
		return list;
	}

}
