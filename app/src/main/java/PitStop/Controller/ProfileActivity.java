package PitStop.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.nio.charset.MalformedInputException;

import PitStop.R;

import static PitStop.Controller.LoginActivity.user;

public class ProfileActivity extends AppCompatActivity {
    TextView username, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar4);
        myToolbar.setTitleTextColor(android.graphics.Color.WHITE);
        setSupportActionBar(myToolbar);

        username = (TextView) findViewById(R.id.nameInput);
        email = (TextView) findViewById(R.id.emailInput);

        username.setText(user.getUsername());
        email.setText(user.getEmail());
    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        if (user.getUserType().equals("Customer")) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.usermenu, menu);
        } else {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.truckmenu, menu);
        }
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_profile:
                // User chose the "Settings" item, show the app settings UI...
                item.setVisible(true);
                return true;

            case R.id.action_mainPage:
                finish();
                this.startActivity(new Intent(ProfileActivity.this, MainFoodTruckUserActivity.class));
                return true;

            case R.id.action_truck:
                // User chose the "Settings" item, show the app settings UI...
                finish();
                this.startActivity(new Intent(ProfileActivity.this, MainUserActivity.class));
                return true;

            case R.id.action_order:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                finish();
                this.startActivity(new Intent(ProfileActivity.this, OrderActivity.class));
                return true;

            case R.id.action_logout:
                // User chose the "Logout" action, mark the current item
                finish();
                this.startActivity(new Intent(ProfileActivity.this, WelcomeScreen.class));
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
