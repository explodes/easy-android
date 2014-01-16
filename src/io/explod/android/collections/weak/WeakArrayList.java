package io.explod.android.collections.weak;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * WeakList backed by an ArrayList.
 * 
 * @author evan
 * 
 * @param <T>
 *            The type of objects to hold WeakReferences to.
 */
public class WeakArrayList<T> extends WeakList<T> {

	/** The underlying list of this WeakArrayList */
	private List<WeakReference<T>> items;

	/**
	 * Base constructor, creates a WeakArrayList backed by a regular ArrayList.
	 */
	public WeakArrayList() {
		this.items = new ArrayList<WeakReference<T>>();
	}

	/**
	 * Construct this WeakArrayList with a given capacity.
	 * 
	 * @param capacity
	 */
	public WeakArrayList(int capacity) {
		this.items = new ArrayList<WeakReference<T>>(capacity);
	}

	/**
	 * Construct this WeakArrayList from a Collection.
	 * 
	 * @param collection
	 */
	public WeakArrayList(Collection<? extends WeakReference<T>> collection) {
		this.items = new ArrayList<WeakReference<T>>(collection);
	}

	@Override
	public List<WeakReference<T>> getItems() {
		return this.items;
	}

}
