package io.explod.easyandroid.async.queue;

import io.explod.easyandroid.async.ICallback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Queue up ExecutionItems and run them all. Get notified of their completion.
 * 
 * @author evan
 * 
 * @param <ExecutionItemType>
 *            Class of the ExecutionItem to use.
 */
public class ExecutionQueue<ExecutionItemType extends ExecutionItem> {

	/**
	 * Callback for ExecutionQueue events
	 * 
	 * @author evan
	 * 
	 * @param <T>
	 */
	public interface IExecutionQueueListener<ExecutionItemType extends ExecutionItem> {
		/**
		 * 
		 * @param sender
		 *            ExecutionQueue that send this event
		 * @param item
		 *            ExecutionItem that finished
		 * @param error
		 *            Error produced during item's execution
		 * @param lastItem
		 *            Was this item the last one?
		 */
		public void onItemComplete(ExecutionQueue<ExecutionItemType> sender, ExecutionItemType item, Exception error, boolean lastItem);
	}

	/**
	 * ExecutionItems to run
	 */
	protected List<ExecutionItemType> items;
	private IExecutionQueueListener<ExecutionItemType> listener;

	/**
	 * Construct a new instance of ExecutionQueue
	 */
	public ExecutionQueue() {
		this.items = new ArrayList<ExecutionItemType>();
		this.items = Collections.synchronizedList(this.items);
	}

	/**
	 * Construct a new instance of ExecutionQueue
	 * 
	 * @param listener
	 *            default listener
	 */
	public ExecutionQueue(IExecutionQueueListener<ExecutionItemType> listener) {
		this();
		this.setExecutionQueueListener(listener);
	}

	/**
	 * Set this queue's execution listener
	 * 
	 * @param listener
	 */
	public void setExecutionQueueListener(IExecutionQueueListener<ExecutionItemType> listener) {
		this.listener = listener;
	}

	/**
	 * Get the current IExecutionQueueListener
	 * 
	 * @return
	 */
	protected IExecutionQueueListener<ExecutionItemType> getExecutionQueueListener() {
		return this.listener;
	}

	/**
	 * Add an ExecutionItem to run
	 * 
	 * @param item
	 */
	public void add(ExecutionItemType item) {
		this.items.add(item);
	}

	/**
	 * Add an ExecutionItem to be ran, assigning a tag to the item, as well.
	 * 
	 * @param tag
	 * @param item
	 */
	public void add(Object tag, ExecutionItemType item) {
		item.setTag(tag);
		this.items.add(item);
	}

	/**
	 * Run all of the items in the queue
	 */
	public void execute() {
		for (final ExecutionItemType item : this.items) {
			this.startItem(item);
		}
	}

	/**
	 * Get the amount of items left to be ran
	 * 
	 * @return
	 */
	public int size() {
		return this.items.size();
	}

	/**
	 * Begin execution of a specific item.
	 * 
	 * @param item
	 */
	protected void startItem(final ExecutionItemType item) {
		item.run(new ICallback() {
			@Override
			public void onComplete(Exception error) {
				ExecutionQueue<ExecutionItemType> self = ExecutionQueue.this;
				self.onItemComplete(item, error);
			}
		});
	}

	/**
	 * Tasks to be performed when a given item finishes.
	 * 
	 * @param item
	 * @param error
	 */
	protected void onItemComplete(ExecutionItemType item, Exception error) {
		this.items.remove(item);
		final IExecutionQueueListener<ExecutionItemType> listener = this.getExecutionQueueListener();
		if (listener != null) {
			final boolean lastItem = this.items.size() == 0;
			listener.onItemComplete(this, item, error, lastItem);
		}
	}
}
