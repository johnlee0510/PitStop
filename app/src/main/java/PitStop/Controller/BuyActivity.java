package PitStop.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import PitStop.R;

public class BuyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar7);
        myToolbar.setTitleTextColor(android.graphics.Color.WHITE);
        setSupportActionBar(myToolbar);


        // Cancel Button
        Button mcancelButton = (Button) findViewById(R.id.cancelButton);
        mcancelButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                // Perform action on click

                Intent intent = new Intent(BuyActivity.this, OrderActivity.class);
                startActivity(intent);
                finish();
                //setContentView(R.layout.activity_login);
            }
        });

        // OrderMore Button
        Button morderButton = (Button) findViewById(R.id.orderButton);
        morderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action on click

                Intent intent = new Intent(BuyActivity.this, OrderActivity.class);
                startActivity(intent);
                finish();
                //setContentView(R.layout.activity_login);
            }
        });
    }
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
                this.startActivity(new Intent(BuyActivity.this, ProfileActivity.class));
                return true;

            case R.id.action_truck:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                this.startActivity(new Intent(BuyActivity.this, MainFoodTruckUserActivity.class));
                return true;

            case R.id.action_order:
                // User chose the "Logout" action, mark the current item
                this.startActivity(new Intent(BuyActivity.this, OrderActivity.class));
                return true;

            case R.id.action_logout:
                // User chose the "Logout" action, mark the current item
                this.startActivity(new Intent(BuyActivity.this, WelcomeScreen.class));
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
