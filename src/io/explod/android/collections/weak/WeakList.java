package io.explod.android.collections.weak;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;

/**
 * WeakArrayList is a basic list to hold weak references to objects and cleans
 * then out when those references go away.
 * 
 * @param <T>
 *            Object type to be a list of.
 * @author evan
 */
public abstract class WeakList<T> {

	/**
	 * Get the items in this list.
	 * 
	 * @return The list of WeakReferences this list is tracking.
	 */
	public abstract List<WeakReference<T>> getItems();

	/**
	 * Create and return a new iterator for this list. See {@link WeakIterator}
	 * 
	 * @return Return the Iterator for this list
	 */
	public Iterator<T> iterator() {
		final List<WeakReference<T>> items = this.getItems();
		return new WeakIterator<T>(items);
	}

	/**
	 * Add an item to this list
	 * 
	 * @param item
	 *            The item to add
	 * @return If the item was actually added
	 */
	public boolean add(T item) {
		final List<WeakReference<T>> items = this.getItems();
		final WeakReference<T> ref = new WeakReference<T>(item);
		return items.add(ref);
	}

	/**
	 * Remove the first occurance of item from the list.
	 * 
	 * @param item
	 *            The item to remove
	 * @return If an item was removed
	 */
	public boolean remove(T item) {
		final Iterator<T> it = this.iterator();
		while (it.hasNext()) {
			final T obj = it.next();
			if (obj.equals(item)) {
				it.remove();
				return true;
			}
		}
		return false;
	}

	/**
	 * Go through the underlying list and remove no-longer-valid WeakReferences.
	 */
	public void clean() {
		final Iterator<T> it = this.iterator();
		while (it.hasNext()) {
			// advance the iterator, this cleans the WeakIterator's underlying
			// list
			it.next();
		}
	}

}
