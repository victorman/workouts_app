package se.frand.app.workout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * A placeholder fragment containing a simple view.
 */
public class WorkoutListFragment extends Fragment {

    private WorkoutListAdapter mWorkoutAdapter;

    public WorkoutListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_workout_list, container, false);

        //Set up the list view and adapter
        ListView listView = (ListView) rootView.findViewById(R.id.workout_list_view);
        //TODO initialize a workout adapter
        //TODO populate the cursor with json data from workouts.json
        mWorkoutAdapter = new WorkoutListAdapter(getActivity(),null,0);
        listView.setAdapter(mWorkoutAdapter);

        //inflate and add the header to the list view
        View headerView = inflater.inflate(R.layout.workout_list_item_header,null,false);
        listView.addHeaderView(headerView,null,false);

        //we want to show the workout plan when the item is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //don't do anything with header and footer
                if(position == 0) {
                    return;
                }
                //TODO show workout plan fragment
                //if TwoPane then show it. if not then replace fragment?
            }
        });

        return rootView;
    }
}