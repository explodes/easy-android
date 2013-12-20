package io.explod.android.collections;

import java.util.ArrayList;

public class NamedListList<T> extends ArrayList<NamedList<T>> {

	private static final long serialVersionUID = 5128077712716235656L;

	public boolean removeFirst(T item) {
		for (final NamedList<T> list : this) {
			if (list.remove(item)) {
				return true;
			}
		}
		return false;
	}

	public boolean removeAny(T item) {
		boolean removed = false;
		for (final NamedList<T> list : this) {
			removed |= list.remove(item);
		}
		return removed;
	}

}
