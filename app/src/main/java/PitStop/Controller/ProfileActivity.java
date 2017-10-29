package PitStop.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
//        // Configure Login Button
//        Button saveButton = (Button) findViewById(R.id.save);
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Perform action on click
//                saveClicked();
//            }
//        });
//
//        // Configure Registration Button
//        Button cancelButton = (Button) findViewById(R.id.cancelProfile);
//        cancelButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Perform action on click
//                cancelClicked();
//            }
//        });
//    }
//
//    private void saveClicked() {
//        Intent intent = new Intent(ProfileActivity.this, MainUserActivity.class);
//        startActivity(intent);
//    }
//
//    private void cancelClicked() {
//        Intent intent2 = new Intent(ProfileActivity.this, MainUserActivity.class);
//        startActivity(intent2);
//    }


    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.usermenu, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_profile:
                // User chose the "Settings" item, show the app settings UI...
                item.setVisible(true);
                return true;

            case R.id.action_truck:
                // User chose the "Settings" item, show the app settings UI...
                this.startActivity(new Intent(ProfileActivity.this, MainUserActivity.class));
                return true;

            case R.id.action_order:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                this.startActivity(new Intent(ProfileActivity.this, OrderActivity.class));
                return true;

            case R.id.action_logout:
                // User chose the "Logout" action, mark the current item
                this.startActivity(new Intent(ProfileActivity.this, WelcomeScreen.class));
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
