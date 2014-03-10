package io.explod.android.data.async.queue;

import io.explod.android.data.async.ICallback;

/**
 * Queue up ExecutionItems and run them all. Get notified of their completion.
 * Items are executed on new threads.
 * 
 * @param <T>
 *            Class of the ExecutionItem to use.
 * @author evan
 */
public class AsyncExecutionQueue<ExecutionItemType extends ExecutionItem> extends ExecutionQueue<ExecutionItemType> {

	/**
	 * Construct a new instance of AsyncExecutionQueue
	 */
	public AsyncExecutionQueue() {
		super();
	}

	/**
	 * Construct a new instance of AsyncExecutionQueue
	 * 
	 * @param listener
	 *            default listener
	 */
	public AsyncExecutionQueue(IExecutionQueueListener<ExecutionItemType> listener) {
		super(listener);
	}

	/**
	 * Begin execution of a specific item. The item is executed on a new thread.
	 * 
	 * @param item
	 */
	@Override
	protected void startItem(final ExecutionItemType item) {

		final Thread t = new Thread() {
			@Override
			public void run() {
				item.run(new ICallback() {
					@Override
					public void onComplete(Exception error) {
						AsyncExecutionQueue<ExecutionItemType> self = AsyncExecutionQueue.this;
						self.onItemComplete(item, error);
					}
				});
			}
		};
		t.start();
	}
}
