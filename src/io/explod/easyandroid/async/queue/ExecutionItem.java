package io.explod.easyandroid.async.queue;

import io.explod.easyandroid.async.ICallback;

/**
 * Runnable object that takes a callback to indicate both completion and error status.
 * 
 * @author evan
 * 
 */
public abstract class ExecutionItem {

	/**
	 * Tag object storage.
	 */
	private Object tag;

	/**
	 * Sets this item's tag.
	 * This can be used during ExecutionQueue.execute, during the `this.run`, or not at all.
	 * 
	 * @param tag
	 *            Information that is desired to be retrieved from this item at a later point.
	 */
	public void setTag(Object tag) {
		this.tag = tag;
	}

	/**
	 * Retrieve this item's tag
	 * 
	 * @return
	 */
	public Object getTag() {
		return this.tag;
	}

	/**
	 * Perform some task.
	 * 
	 * @param callback
	 *            Callback must be called when this item has finished. It indications both completion and error status.
	 */
	public abstract void run(ICallback callback);
}
