package io.explod.android.app.view.adapter;

import io.explod.android.collections.NamedList;
import io.explod.android.collections.NamedListList;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

/**
 * Quick and easy class for ExpandableListAdapter.
 * 
 * @author evan
 * 
 * @param <DataType>
 *            Data type contained in this adapter.
 * @param <GroupView>
 *            View type for groups.
 * @param <ChildView>
 *            View type for children.
 */
public abstract class NamedListExpandableListAdapter<DataType, GroupView extends View, ChildView extends View> extends BaseExpandableListAdapter {

	private Context context;
	private NamedListList<DataType> data;

	public NamedListExpandableListAdapter(Context context, NamedListList<DataType> data) {
		super();
		this.context = context;
		this.data = data;
	}

	@Override
	public int getGroupCount() {
		return this.data.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		final NamedList<DataType> data = this.data.get(groupPosition);
		return data.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		final NamedList<DataType> data = this.data.get(groupPosition);
		return data;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		final NamedList<DataType> data = this.data.get(groupPosition);
		final DataType item = data.get(childPosition);
		return item;
	}

	@Override
	public long getGroupId(int groupPosition) {
		final NamedList<DataType> data = this.data.get(groupPosition);
		final String name = data.getName();
		final long id = name.hashCode();
		return id;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		final NamedList<DataType> data = this.data.get(groupPosition);
		final DataType item = data.get(childPosition);
		final long id = item.hashCode();
		return id;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		final GroupView group;
		if (convertView == null) {
			final Context context = this.getContext();
			group = this.createGroupView(context);
		} else {
			group = (GroupView) convertView;
		}
		final NamedList<DataType> namedList = this.data.get(groupPosition);
		this.updateGroupView(group, namedList, isExpanded);
		return group;
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		final ChildView child;
		if (convertView == null) {
			final Context context = this.getContext();
			child = this.createChildView(context);
		} else {
			child = (ChildView) convertView;
		}
		final NamedList<DataType> data = this.data.get(groupPosition);
		final DataType item = data.get(childPosition);
		this.updateChildView(child, item, isLastChild);
		return child;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		final NamedList<DataType> data = this.data.get(groupPosition);
		final DataType item = data.get(childPosition);
		return this.isChildSelectable(data, item);
	}

	public Context getContext() {
		return this.context;
	}

	/**
	 * Create a GroupView for this Expandable List
	 * 
	 * @param context
	 *            Context to build a view from
	 * @return Brand new View
	 */
	public abstract GroupView createGroupView(Context context);

	/**
	 * Update a GroupView for this Expandable List with data
	 * 
	 * @param view
	 *            GroupView to update
	 * @param data
	 *            NamedList to update the GroupView with
	 * @param isExpanded
	 *            Whether or not the GroupView is expanded
	 */
	public abstract void updateGroupView(GroupView view, NamedList<DataType> data, boolean isExpanded);

	/**
	 * Create a ChildView for this Expandable List
	 * 
	 * @param context
	 *            Context to build a view from
	 * @return Brand new View
	 */
	public abstract ChildView createChildView(Context context);

	/**
	 * Update a ChildView for this Expandable List with data
	 * 
	 * @param view
	 *            ChildView to update
	 * @param item
	 *            Data to update the ChildView with
	 * @param isLastChild
	 *            Whether or not this is the last child in the group
	 */
	public abstract void updateChildView(ChildView view, DataType item, boolean isLastChild);

	/**
	 * Whether or not a ChildView should be selectable
	 * 
	 * @param data
	 *            Data list the child belongs under
	 * @param item
	 *            Data the child has
	 * @return Enabledness of selectivity
	 */
	public abstract boolean isChildSelectable(NamedList<DataType> data, DataType item);

}
