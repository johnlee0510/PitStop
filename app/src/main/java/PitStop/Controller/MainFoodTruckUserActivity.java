package PitStop.Controller;
/**
 * Created by John on 10/28/2017.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import PitStop.R;

public class MainFoodTruckUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_food_truck_user);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.truckmenu);
        myToolbar.setTitleTextColor(android.graphics.Color.WHITE);
        setSupportActionBar(myToolbar);
    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.truckmenu, menu);

        return true;

    }
    @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_profile:
                    // User chose the "Settings" item, show the app settings UI...
                    this.startActivity(new Intent(MainFoodTruckUserActivity.this, ProfileActivity.class));
                    return true;

                case R.id.action_truck:
                    // User chose the "Favorite" action, mark the current item
                    // as a favorite...
                    item.setVisible(false);
                    return true;

                case R.id.action_queue:
                    // User chose the "Logout" action, mark the current item
                    this.startActivity(new Intent(MainFoodTruckUserActivity.this, QueueActivity.class));
                    return true;

                case R.id.action_logout_truck:
                    // User chose the "Logout" action, mark the current item
                    Toast.makeText(getApplicationContext(), "Sign off, good bye", Toast.LENGTH_SHORT).show();
                    this.startActivity(new Intent(MainFoodTruckUserActivity.this, WelcomeScreen.class));
                    return true;
                default:
                    // If we got here, the user's action was not recognized.
                    // Invoke the superclass to handle it.
                    return super.onOptionsItemSelected(item);

            }
    }
}
