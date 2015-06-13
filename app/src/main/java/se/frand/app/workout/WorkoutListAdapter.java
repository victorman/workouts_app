package se.frand.app.workout;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by victorfrandsen on 6/12/15.
 */
public class WorkoutListAdapter extends CursorAdapter {

    //TODO store a workout item for workout exercises.

    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_GENERAL = 1;
    private static final int VIEW_TYPE_COUNT = 2;

    public WorkoutListAdapter(Context context, Cursor cursor, int flags) {
        super(context,cursor,flags);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        //TODO populate a list item view with workouts, workout titles, and times
        if(getItemViewType(cursor.getPosition()) == VIEW_TYPE_GENERAL) {
            ViewHolder viewHolder = (ViewHolder) view.getTag();

            viewHolder.titleView.setText("Workout " + cursor.getPosition());

            viewHolder.durationView.setText("10m");
        }
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        int viewType = getItemViewType(cursor.getPosition());


        int layoutId = -1;

        switch (viewType) {
            case VIEW_TYPE_GENERAL: {
                layoutId = R.layout.workout_list_item;
                break;
            }
            case VIEW_TYPE_HEADER: {
                layoutId = R.layout.workout_list_item_header;
                break;
            }
        }

        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);

        return view;
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == 0) ? VIEW_TYPE_HEADER : VIEW_TYPE_GENERAL;
    }

    public static class ViewHolder {
        //TODO fill out view holder
        // is this a view holder for a header or either?
        //add public instance variables for view elements
        public final TextView titleView;
        public final TextView durationView;

        public ViewHolder(View view) {
            titleView = (TextView) view.findViewById(R.id.workout_item_title_view);
            durationView = (TextView) view.findViewById(R.id.workout_item_duration_view);
        }
    }
}
