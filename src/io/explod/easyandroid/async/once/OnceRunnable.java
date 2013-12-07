package io.explod.easyandroid.async.once;

import io.explod.easyandroid.async.IResultCallback;

/**
 * Interface to allow Oncer's to perform async calls and still receive results.
 * 
 * @author evan
 * 
 * @param <DataType>
 * @param <ErrorType>
 */
public interface OnceRunnable<DataType, ErrorType> {

	/**
	 * Runnable that calls a "callback" on completion.
	 * 
	 * @param callback
	 */
	public abstract void run(IResultCallback<DataType, ErrorType> callback);

}
