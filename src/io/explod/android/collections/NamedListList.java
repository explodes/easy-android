package io.explod.android.collections;

import java.util.ArrayList;
import java.util.List;

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

	/**
	 * Remove empty lists from this list of lists
	 * 
	 * @return Whether or not any lists were cleared out
	 */
	public boolean removeEmptyLists() {
		final List<NamedList<T>> removeMe = new ArrayList<NamedList<T>>();
		for (final NamedList<T> list : this) {
			if (list.size() == 0) {
				removeMe.add(list);
			}
		}
		for (final NamedList<T> list : removeMe) {
			this.remove(list);
		}
		return removeMe.size() > 0;
	}

}
