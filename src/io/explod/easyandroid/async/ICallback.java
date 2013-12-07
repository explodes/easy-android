package io.explod.easyandroid.async;

/**
 * Simple callback to indicate completion and error status.
 * 
 * @author evan
 * 
 */
public interface ICallback {

	/**
	 * Callback functionality.
	 * 
	 * @param error
	 */
	public void onComplete(Exception error);

}
