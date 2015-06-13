package se.frand.app.workout;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class WelcomeActivity extends ActionBarActivity {

    private static final String WORKOUT_FRAGMENT_TAG = "WORKOUT_FRAGMENT";

    private boolean mTwoPaneMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        if(findViewById(R.id.workout_plan_container) != null) {
            mTwoPaneMode = true;
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.workout_plan_container, new WorkoutListFragment(), WORKOUT_FRAGMENT_TAG)
                        .commit();
            }
        } else {
            mTwoPaneMode = false;
        }

        WorkoutListFragment listFragment = ((WorkoutListFragment) getSupportFragmentManager()
            .findFragmentById(R.id.fragment_workout_list));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
