package io.explod.easyandroid.remote.once;

import java.util.ArrayList;
import java.util.List;

import android.os.Handler;

/**
 * Oncer pool object.
 * Stores results from one-time operations.
 * You will use Oncer.once and Oncer.resetKey for the bulk of your operations.
 * 
 * @author evan
 * 
 * @param <KeyType>
 * @param <DataType>
 * @param <ErrorType>
 */
public abstract class Oncer<KeyType, DataType, ErrorType> {

	private static final long POST_DELAY = 100;

	private List<KeyType> pendingLookups;

	public Oncer() {
		this.pendingLookups = new ArrayList<KeyType>();
	}

	// interface

	/**
	 * Add a result to the Oncer pool
	 * 
	 * @param key
	 * @param result
	 */
	public abstract void putResult(KeyType key, Result<DataType, ErrorType> result);

	/**
	 * Get a result from the Oncer pool
	 * 
	 * @param key
	 * @return
	 */
	public abstract Result<DataType, ErrorType> getResult(KeyType key);

	/**
	 * Test the oncer pool for the containment of a result
	 * 
	 * @param key
	 * @return
	 */
	public abstract boolean hasResult(KeyType key);

	/**
	 * Clear a key from history so that calls can be made with that key again
	 * 
	 * @param key
	 */
	public abstract void resetKey(KeyType key);

	// public

	/**
	 * Run a callable only one time per key.
	 * Results from the first call are used.
	 * Can be called multiple times simultaneously and the runnable will be called only once.
	 * 
	 * @param key
	 * @param runnable
	 * @param callback
	 */
	public synchronized void once(final KeyType key, final OnceRunnable<DataType, ErrorType> runnable, final Callback<DataType, ErrorType> callback) {
		final boolean hasResult = this.hasResult(key);
		if (hasResult) {
			this.callCallbackWithKey(key, callback);
		} else {
			final boolean isPending = this.isPending(key);
			if (isPending) {
				this.holdingPattern(key, callback);
			} else {
				this.executeRunnable(key, runnable, callback);
			}
		}
	}

	/**
	 * This method calls a callback with data and errors.
	 * Subclasses can perform the callback on a new thread, if they so desire.
	 * 
	 * @param data
	 * @param error
	 * @param callback
	 */
	protected void callCallbackWithData(final DataType data, final ErrorType error, final Callback<DataType, ErrorType> callback) {
		callback.onResult(data, error);
	}

	// privates

	/**
	 * test a key to see if it is pending
	 * 
	 * @param key
	 * @return
	 */
	private boolean isPending(final KeyType key) {
		return this.pendingLookups.contains(key);
	}

	/**
	 * explode a result by key and call a callback with the data
	 * 
	 * @param key
	 * @param callback
	 */
	private void callCallbackWithKey(final KeyType key, final Callback<DataType, ErrorType> callback) {
		final Result<DataType, ErrorType> result = this.getResult(key);
		this.callCallbackWithResult(result, callback);
	}

	/**
	 * explode a result and call a callback with the data
	 * 
	 * @param result
	 * @param callback
	 */
	private void callCallbackWithResult(final Result<DataType, ErrorType> result, final Callback<DataType, ErrorType> callback) {
		final DataType data = result.getData();
		final ErrorType error = result.getError();
		this.callCallbackWithData(data, error, callback);
	}

	/**
	 * enter a holding pattern, waiting until a key is no longer pending
	 * 
	 * @param key
	 * @param callback
	 */
	private void holdingPattern(final KeyType key, final Callback<DataType, ErrorType> callback) {
		final Handler handler = new Handler();
		final Runnable runnable = new Runnable() {
			@Override
			public void run() {
				final Oncer<KeyType, DataType, ErrorType> self = Oncer.this;
				final boolean isPending = self.isPending(key);
				if (isPending) {
					handler.postDelayed(this, POST_DELAY);
				} else {
					self.callCallbackWithKey(key, callback);
				}
			}
		};
		handler.postDelayed(runnable, POST_DELAY);
	}

	/**
	 * execute a runnable, tracking pending status and saving results
	 * 
	 * @param key
	 * @param runnable
	 * @param callback
	 */
	private synchronized void executeRunnable(final KeyType key, final OnceRunnable<DataType, ErrorType> runnable, final Callback<DataType, ErrorType> callback) {
		this.pendingLookups.add(key);
		final Callback<DataType, ErrorType> onComplete = new Callback<DataType, ErrorType>() {
			@Override
			public void onResult(DataType data, ErrorType error) {
				final Oncer<KeyType, DataType, ErrorType> self = Oncer.this;
				final Result<DataType, ErrorType> result = new Result<DataType, ErrorType>(data, error);
				self.putResult(key, result);
				self.pendingLookups.remove(key);
				self.callCallbackWithData(data, error, callback);
			}
		};
		runnable.run(onComplete);
	}
}
