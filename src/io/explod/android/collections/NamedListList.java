package io.explod.android.collections;

import java.util.ArrayList;

/**
 * A List of NamedList<T>. Useful for NamedListExpandableListAdapters.
 * 
 * @author evan
 * 
 * @param <T>
 *            Type of data this list of lists holds.
 */
public class NamedListList<T> extends ArrayList<NamedList<T>> {

	private static final long serialVersionUID = 5128077712716235656L;

	/**
	 * Remove the first occurrence of {@code item}
	 * 
	 * @param item
	 *            The item to remove
	 * @return Whether or not the item was removed
	 */
	public boolean removeFirst(T item) {
		for (final NamedList<T> list : this) {
			if (list.remove(item)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Remove all occurrences of {@code item}
	 * 
	 * @param item
	 *            The item to remove
	 * @return Whether or not any items were removed
	 */
	public boolean removeAny(T item) {
		boolean removed = false;
		for (final NamedList<T> list : this) {
			removed |= list.remove(item);
		}
		return removed;
	}

}
