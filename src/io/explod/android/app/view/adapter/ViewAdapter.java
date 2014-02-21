package io.explod.android.app.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Quick and easy to use ArrayAdapter that constructs and updates views.
 *
 * @param <ViewType> The ViewType this ViewAdapter will construct
 * @param <DataType> The DataType this ViewAdapter will assign to views
 * @author evan
 */
public abstract class ViewAdapter<ViewType extends View, DataType> extends ArrayAdapter<DataType> {

    /**
     * Underlying data storage
     */
    private List<DataType> data;

    /**
     * Construct this ViewAdapter without data.<br/>
     * An ArrayList of DataType will be created that you can retrieve with {@link #getData()}
     *
     * @param context Context with which ViewTypes will be constructed with
     */
    public ViewAdapter(Context context) {
        this(context, new ArrayList<DataType>());
    }

    /**
     * Construct this ViewAdapter with data.<br/>
     * Data can be later retrieved via {@link #getData()}
     *
     * @param context Context with which ViewTypes will be constructed with
     * @param data    Initial data
     */
    public ViewAdapter(Context context, List<DataType> data) {
        super(context, 0, data);
        this.data = data;
    }

    @SuppressWarnings("unchecked")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewType view;
        final int viewType = this.getItemViewType(position);
        if (convertView == null) {
            final Context context = this.getContext();
            view = this.createView(context, viewType);
        } else {
            view = (ViewType) convertView;
        }

        final DataType data = this.getItem(position);
        this.updateView(view, data, viewType);
        return view;
    }

    /**
     * Get an individual point of data at a position.
     *
     * @param position The position to get data from
     * @return The data item at the given position
     */
    public DataType getItem(int position) {
        return this.data.get(position);
    }

    /**
     * Return the underlying data adapted to by this ViewAdapter. <br/>
     * Manipulation of this data will still require a call to {@link #notifyDataSetChanged()}
     *
     * @return Either the data this object was constructed with, or the ArrayList it created as a placeholder.
     */
    public List<DataType> getData() {
        return this.data;
    }

    /**
     * Create, and only create, a new instance of ViewType
     *
     * @param context  Context to construct the view with
     * @param viewType The view type to create, as specified by with {@link #getItemViewType(int)}
     * @return A new, unpopulated, ViewType
     */
    public abstract ViewType createView(Context context, int viewType);

    /**
     * Populate a ViewType view with DataType data
     *
     * @param view     View to populate with data
     * @param data     Data to populate view with
     * @param viewType The view type to populate, as specified by with {@link #getItemViewType(int)}
     */
    public abstract void updateView(ViewType view, DataType data, int viewType);

}
