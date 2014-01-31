package io.explod.android.collections.weak;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;

/**
 * Iterator that prunes itself from no-longer-valid WeakReferences.
 *
 * @param <T> Type of Objects being iterated
 * @author evan
 */
public class WeakIterator<T> implements Iterator<T> {

    /**
     * The underlying iterator being pruned
     */
    private Iterator<WeakReference<T>> iterator;
    /**
     * The next object to return
     */
    private T next = null;

    /**
     * Construct this WeakIterator to iterate the given list
     *
     * @param items The list of items to iterate
     */
    public WeakIterator(List<WeakReference<T>> items) {
        this.iterator = items.iterator();
        this.advance();
    }

    /**
     * Advance the underlying iterator to the next valid WeakReference. Prunes
     * no-longer-valid WeakReferences.
     */
    private void advance() {
        this.next = null;
        while (this.iterator.hasNext() && this.next == null) {
            final WeakReference<T> ref = this.iterator.next();
            this.next = ref.get();
            if (this.next == null) {
                this.iterator.remove();
            }
        }
    }

    @Override
    public boolean hasNext() {
        return this.next != null;
    }

    @Override
    public T next() {
        T next = this.next;
        this.advance();
        return next;
    }

    @Override
    public void remove() {
        this.iterator.remove();
    }

}
