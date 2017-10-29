package PitStop.Controller;
/**
 * Created by John on 10/28/2017.
 */

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import PitStop.Model.RequestHandler;
import PitStop.Model.SharedPrefManager;
import PitStop.Model.URLs;
import PitStop.Model.User;
import PitStop.R;

public class RegistrationActivity extends AppCompatActivity {

    EditText editTextUsername, editTextEmail, editTextPassword;
    RadioGroup radioGroupUserType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        //if the user is already logged in we will directly start the profile activity
//        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
//            finish();
//            startActivity(new Intent(this, ProfileActivity.class));
//            return;
//        }

        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        radioGroupUserType = (RadioGroup) findViewById(R.id.radioUserType);


        // Configure Forgot Password Button
        TextView EmailRecoveryButton = (TextView) findViewById(R.id.textViewEmailRecovery);
        EmailRecoveryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                startActivity(new Intent(RegistrationActivity.this, EmailRecoveryActivity.class));
            }
        });


        findViewById(R.id.buttonRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if user pressed on button register
                //here we will register the user to server
                registerUser();
            }
        });

        findViewById(R.id.textViewLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if user pressed on login
                //we will open the login screen
                finish();
                startActivity(new Intent(RegistrationActivity.this, WelcomeScreen.class));
            }
        });

    }

    private void registerUser() {
        final String username = editTextUsername.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String userType = ((RadioButton) findViewById(radioGroupUserType.getCheckedRadioButtonId())).getText().toString().trim();
        final String emailValidatePattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        //first we will do the validations
        Log.d("password ", password);
        Log.d("userType ", userType);
        if (TextUtils.isEmpty(username)) {
            editTextUsername.setError("Please enter username");
            editTextUsername.requestFocus();
            return;
        } else if (!email.matches(emailValidatePattern)) {
            editTextEmail.setError("Please type a valid email");
            editTextEmail.requestFocus();
        } else if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Please enter your email");
            editTextEmail.requestFocus();
            return;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter a valid email");
            editTextEmail.requestFocus();
            return;
        } else if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Enter a password");
            editTextPassword.requestFocus();
            return;
        } else {
            //if it passes all the validations

            class RegisterUser extends AsyncTask<Void, Void, String> {

                private ProgressBar progressBar;

                @Override
                protected String doInBackground(Void... voids) {
                    //creating request handler object
                    RequestHandler requestHandler = new RequestHandler();

                    //creating request parameters
                    HashMap<String, String> params = new HashMap<>();
                    params.put("username", username);
                    params.put("emailAddress", email);
                    params.put("password", password);
                    params.put("userType", userType);

                    //returing the response
                    return requestHandler.sendPostRequest(URLs.URL_REGISTER, params);
                }

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    //displaying the progress bar while user registers on the server
                    progressBar = (ProgressBar) findViewById(R.id.progressBar);
                    progressBar.setVisibility(View.VISIBLE);
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    //hiding the progressbar after completion
                    progressBar.setVisibility(View.GONE);

                    try {
                        //converting response to json object
                        JSONObject obj = new JSONObject(s);

                        //if no error in response
                        if (!obj.getBoolean("error")) {
                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                            //getting the user from the response
                            JSONObject userJson = obj.getJSONObject("user");

                            //creating a new user object
                            User user = new User(
                                    userJson.getInt("id"),
                                    userJson.getString("username"),
                                    userJson.getString("password"),
                                    userJson.getString("emailAddress"),
                                    userJson.getString("userType")
                            );

                            //storing the user in shared preferences
                            SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                            //starting the profile activity
                            finish();
                            startActivity(new Intent(getApplicationContext(), WelcomeScreen.class));
                        } else {
                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            }

            //executing the async task
            RegisterUser ru = new RegisterUser();
            ru.execute();
        }
    }
}