package io.explod.easyandroid.iface;

import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Interface to quickly cast / find views by id.
 * 
 * @author evan
 * 
 */
public interface IFindViews {

	public abstract TextView findTextViewById(int id);

	public abstract Button findButtonById(int id);

	public abstract EditText findEditTextById(int id);

	public abstract ImageView findImageViewById(int id);

	public abstract ImageButton findImageButtonById(int id);

	public abstract GridView findGridViewById(int id);

	public abstract ListView findListViewById(int id);

}
