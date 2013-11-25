package io.explod.easyandroid.iface;

public interface IGetLayoutId {

	/**
	 * Return the layout ID for this activity. Return 0 if you do not wish to
	 * automatically inflate.
	 * 
	 * @return
	 */
	public abstract int getLayoutId();
}