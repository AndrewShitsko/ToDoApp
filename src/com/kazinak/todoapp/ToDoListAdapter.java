package com.kazinak.todoapp;

import java.util.ArrayList;
import java.util.List;
import com.kazinak.todoapp.ToDoItem.Status;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ToDoListAdapter extends BaseAdapter {

    private final List<ToDoItem> mItems = new ArrayList<ToDoItem>();
    private final Context mContext;

    private static final String TAG = "Lab-UserInterface";

    static class ViewHolder {
        public TextView title;
        public CheckBox status;
        public TextView priority;
        public TextView date;
    }

    public ToDoListAdapter(Context context) {

        mContext = context;

    }

    // Add a ToDoItem to the adapter
    // Notify observers that the data set has changed

    public void add(ToDoItem item) {

        mItems.add(item);
        notifyDataSetChanged();

    }

    // Clears the list adapter of all items.

    public void clear() {

        mItems.clear();
        notifyDataSetChanged();

    }

    // Returns the number of ToDoItems

    @Override
    public int getCount() {

        return mItems.size();

    }

    // Retrieve the number of ToDoItems

    @Override
    public Object getItem(int pos) {

        return mItems.get(pos);

    }

    // Get the ID for the ToDoItem
    // In this case it's just the position

    @Override
    public long getItemId(int pos) {

        return pos;

    }

    // Create a View for the ToDoItem at specified position
    // Remember to check whether convertView holds an already allocated View
    // before created a new View.
    // Consider using the ViewHolder pattern to make scrolling more efficient
    // See:
    // http://developer.android.com/training/improving-layouts/smooth-scrolling.html

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // TODO - Get the current ToDoItem
        final ToDoItem toDoItem = (ToDoItem) getItem(position);

        // TODO - Inflate the View for this ToDoItem
        // from todo_item.xml

        RelativeLayout itemLayout = (RelativeLayout) convertView;
        if (itemLayout == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemLayout = (RelativeLayout) inflater.inflate(R.layout.todo_item,
                    null);
            // TODO - Fill in specific ToDoItem data
            // Remember that the data that goes in this View
            // corresponds to the user interface elements defined
            // in the layout file
            ViewHolder holder = new ViewHolder();
            holder.title = (TextView) itemLayout.findViewById(R.id.titleView);
            holder.status = (CheckBox) itemLayout
                    .findViewById(R.id.statusCheckBox);
            holder.priority = (TextView) itemLayout
                    .findViewById(R.id.priorityView);
            holder.date = (TextView) itemLayout.findViewById(R.id.dateView);
            itemLayout.setTag(holder);
        }

        final ViewHolder viewHolder = (ViewHolder) itemLayout.getTag();
        // TODO - Display Title in TextView
        String title = toDoItem.getTitle();
        viewHolder.title.setText(title);

        // TODO - Set up Status CheckBox
        viewHolder.status
                .setChecked(toDoItem.getStatus() == ToDoItem.Status.DONE);

        viewHolder.status
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        Log.i(TAG, "Entered onCheckedChanged()");

                        // TODO - set up an OnCheckedChangeListener, which
                        // is called when the user toggles the status checkbox
                        if (isChecked) {
                            toDoItem.setStatus(Status.DONE);
                            viewHolder.status.setChecked(true);
                        } else {
                            toDoItem.setStatus(Status.NOTDONE);
                            viewHolder.status.setChecked(false);
                        }
                    }
                });

        // TODO - Display Priority in a TextView
        String priority = toDoItem.getPriority().toString();
        viewHolder.priority.setText(priority);

        // TODO - Display Time and Date.
        // Hint - use ToDoItem.FORMAT.format(toDoItem.getDate()) to get date and
        // time String

        viewHolder.date.setText(ToDoItem.FORMAT.format(toDoItem.getDate()));

        // Return the View you just created
        return itemLayout;

    }
}
