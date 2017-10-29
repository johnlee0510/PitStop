package PitStop.Controller;
/**
 * Created by John on 10/28/2017.
 */
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;

import PitStop.Model.RequestHandler;
import PitStop.Model.SharedPrefManager;
import PitStop.Model.URLs;
import PitStop.Model.User;
import PitStop.R;

import static PitStop.Controller.LoginActivity.user;

public class MainFoodTruckUserActivity extends AppCompatActivity {
    TextView truckName;
    EditText editMaxLimit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_food_truck_user);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.truckmenu);
        myToolbar.setTitleTextColor(android.graphics.Color.WHITE);
        setSupportActionBar(myToolbar);
        truckName = (TextView) findViewById(R.id.truckName);
        truckName.setText(user.getUsername());
        editMaxLimit = (EditText) findViewById(R.id.editMaxLimit);
        //if user presses on login
        //calling the method login
        findViewById(R.id.FoodTruckConfirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limitation();
            }
        });
    }
    private void limitation() {
        //first getting the values
        final String maxLimit = editMaxLimit.getText().toString();
        final String truck_name = user.getUsername();
        //validating inputs
        if (TextUtils.isEmpty(maxLimit)) {
            editMaxLimit.setError("Please enter maximum Limitation");
            editMaxLimit.requestFocus();
            return;
        }
    class SetLimitation extends AsyncTask<Void, Void, String> {

        ProgressBar progressBar;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar = (ProgressBar) findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.GONE);
            try {
                //converting response to json object
                JSONObject obj = new JSONObject(s);

                //if no error in response
                if (!obj.getBoolean("error")) {
                    Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();

            }
        }

        @Override
        protected String doInBackground(Void... voids) {
            //creating request handler object
            RequestHandler requestHandler = new RequestHandler();

            //creating request parameters
            HashMap<String, String> params = new HashMap<>();
            params.put("truck_name", truck_name);
            params.put("max_limit", maxLimit);

            //returing the response
            return requestHandler.sendPostRequest(URLs.URL_MAX_LIMIT, params);
        }
    }

    SetLimitation sl = new SetLimitation();
        sl.execute();
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
