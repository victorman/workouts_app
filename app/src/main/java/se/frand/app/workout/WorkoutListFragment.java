package se.frand.app.workout;

import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * A
 */
public class WorkoutListFragment extends Fragment {
    public static final String TAG = WorkoutListFragment.class.getSimpleName();

    private WorkoutListAdapter mWorkoutAdapter;

    public WorkoutListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_workout_list, container, false);

        //Set up the list view and adapter
        ListView listView = (ListView) rootView.findViewById(R.id.workout_list_view);
        //put json data into an object for adapter
        //populate the cursor with json data from workouts.json
        JSONArray workoutArray = loadJSONData();
        MatrixCursor c = new MatrixCursor(new String[] {"_id", "name"});
        try {
            for (int i = 0; i < workoutArray.length(); i++) {
                JSONObject o = workoutArray.getJSONObject(i);
                c.addRow(new String[] {""+i, o.getString("name")});
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //inflate and add the header to the list view
        //set header view before using setAdapter. up to kitkat
        View headerView = inflater.inflate(R.layout.workout_list_item_header,listView,false);
        listView.addHeaderView(headerView, null, false);

        // initialize a workout list adapter
        mWorkoutAdapter = new WorkoutListAdapter(getActivity(),c,0);
        listView.setAdapter(mWorkoutAdapter);

        //we want to show the workout plan when the item is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v(TAG, "checking we hit onItemClick");

                //if in two pane mode add fragment to workout plan container. otherwise add it to fragment workout list
                if(false) {
                    //TODO add two pane logic
                } else {
                    //TODO show workout plan fragment
                    //if TwoPane then show it. if not then replace fragment?
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(
                                    R.id.list_workouts_fragment_segment_small,
                                    new WorkoutPlanViewFragment()
                            )
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

        return rootView;
    }


    private JSONArray loadJSONData() {
        JSONArray json = null;

        try {
            InputStream stream = getResources().openRawResource(R.raw.workouts);
            int size = stream.available();
            byte[] buff = new byte[size];
            stream.read(buff);
            stream.close();
            json = (new JSONObject(new String(buff, "UTF-8"))).getJSONArray("workouts");

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return json;
    }
}