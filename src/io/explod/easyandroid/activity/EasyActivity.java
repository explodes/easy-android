package io.explod.easyandroid.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public abstract class EasyActivity extends Activity implements IEasyActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final boolean inflated = this.inflate();
		if (inflated) {
			this.setFonts(this);
		}
	}

	/**
	 * Inflate with this activity's `getLayoutId` result, if it is non-zero
	 * 
	 * @return
	 */
	protected boolean inflate() {
		int id = this.getLayoutId();
		if (id != 0) {
			this.setContentView(id);
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
