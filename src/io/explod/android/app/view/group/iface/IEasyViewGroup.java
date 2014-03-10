package io.explod.android.app.view.group.iface;

import android.content.Context;

import io.explod.android.app.iface.IInflate;
import io.explod.android.app.iface.ISetFonts;

/**
 * Interface for Easy View Groups <br/>
 * Includes the things that one must usually always do, like picking a layout
 * resId, and setting fonts.
 * 
 * @author evan
 */
public interface IEasyViewGroup extends IInflate, ISetFonts {

	/**
	 * Called after inflation, which is called after construction. This is
	 * called before setFonts.
	 * <p/>
	 * Perform you initialization code here.
	 * 
	 * @param context
	 *            Context that started this view.
	 */
	public void start(Context context);

}
