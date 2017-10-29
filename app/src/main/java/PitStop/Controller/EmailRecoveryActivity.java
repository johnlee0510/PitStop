package PitStop.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import PitStop.Model.GMailSender;
import PitStop.R;

public class EmailRecoveryActivity extends AppCompatActivity {

    EditText meditTextUsername, meditEmailAddress;

    final String usernameGmail = "CS2340test01";
    final String passwordGmail = "@@Love2340";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_recovery);


        meditTextUsername = (EditText) findViewById(R.id.editTextUsername);
        meditEmailAddress = (EditText) findViewById(R.id.editEmailAddress);

        // Configure Cancel Button
        TextView mCancelButton = (TextView) findViewById(R.id.buttonCancel);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                startActivity(new Intent(EmailRecoveryActivity.this, WelcomeScreen.class));
            }
        });

        // Configure Forgot Password Button
        Button mRecovery = (Button) findViewById(R.id.buttonRecovery);
        mRecovery.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                recoveryClicked();
            }
        });
    }

//    private void recoveryClicked() {
//
//        final String username = meditTextUsername.getText().toString();
//        final String emailAddress = meditEmailAddress.getText().toString();
//
//        //validating inputs
//        if (TextUtils.isEmpty(username)) {
//            meditTextUsername.setError("Please enter your username");
//            meditTextUsername.requestFocus();
//            return;
//        }
//
//        if (TextUtils.isEmpty(emailAddress)) {
//            meditEmailAddress.setError("Please enter your password");
//            meditEmailAddress.requestFocus();
//            return;
//        }
//    }

    private void recoveryClicked() {
        // Database check
        //startActivity(new Intent(EmailRecoveryActivity.this, EmailRecoveryActivity.class));

        final String username = meditTextUsername.getText().toString();
        final String emailAddress = meditEmailAddress.getText().toString();

        //validating inputs
        if (TextUtils.isEmpty(username)) {
            meditTextUsername.setError("Please enter your username");
            meditTextUsername.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(emailAddress)) {
            meditEmailAddress.setError("Please enter your password");
            meditEmailAddress.requestFocus();
            return;
        }

        // TODO Auto-generated method stub
        //Check in database !!! John
        try {
            GMailSender sender = new GMailSender("CS2340test01@gmail.com", "@@Love2340");
            sender.sendMail("Email Password Recovery of ",
                    "This is Body",
                    "cs2340test01@gmail.com",
                    "gzhcsteel@gmail.com");
            Log.d("SendMail", "***********");

            //String subject, String body, String sender, String recipients
        } catch (Exception e) {
            Log.e("SendMail", e.getMessage(), e);
        }



    }
}
