package PitStop.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import PitStop.R;

public class EmailRecoveryActivity extends AppCompatActivity {

    EditText meditTextUsername, meditEmailAddress;

    final String usernameGmail = "CS2340test01";
    final String passwordGmail = "@loveCS2340";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_recovery);


        meditTextUsername = (EditText) findViewById(R.id.editTextUsername);
        meditEmailAddress = (EditText) findViewById(R.id.editEmailAddress);

        // Configure Cancel Button
        TextView mCancelButton = (TextView) findViewById(R.id.cancelButton);
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

    private void recoveryClicked() {

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

//        if() {
//            Intent i = new Intent(Intent.ACTION_SEND);
//            i.setType("message/rfc822");
//            i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"recipient@example.com"});
//            i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
//            i.putExtra(Intent.EXTRA_TEXT   , "body of email");
//            try {
//                startActivity(Intent.createChooser(i, "Send mail..."));
//            } catch (android.content.ActivityNotFoundException ex) {
//                Toast.makeText(EmailRecoveryActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
//            }
//        }
    }




}

//    private void recoveryClicked() {
//        // Database check
//        //startActivity(new Intent(EmailRecoveryActivity.this, EmailRecoveryActivity.class));
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
//
//        //Check in database !!! John
//
//    }
//
//
//    /**
//     * getEmail send emails to user.
//     * @return String
//     */
//    public class SendMailTask extends AsyncTask {
//
//        private ProgressDialog statusDialog;
//        private Activity sendMailActivity;
//
//        public SendMailTask(Activity activity) {
//            sendMailActivity = activity;
//
//        }
//
//        protected void onPreExecute() {
//            statusDialog = new ProgressDialog(sendMailActivity);
//            statusDialog.setMessage("Getting ready...");
//            statusDialog.setIndeterminate(false);
//            statusDialog.setCancelable(false);
//            statusDialog.show();
//        }
//
//        @Override
//        protected Object doInBackground(Object... args) {
//            try {
//                Log.i("SendMailTask", "About to instantiate GMail...");
//                publishProgress("Processing input....");
//                GMail androidEmail = new GMail(args[0].toString(),
//                        args[1].toString(), (List) args[2], args[3].toString(),
//                        args[4].toString());
//                publishProgress("Preparing mail message....");
//                androidEmail.createEmailMessage();
//                publishProgress("Sending email....");
//                androidEmail.sendEmail();
//                publishProgress("Email Sent.");
//                Log.i("SendMailTask", "Mail Sent.");
//            } catch (Exception e) {
//                publishProgress(e.getMessage());
//                Log.e("SendMailTask", e.getMessage(), e);
//            }
//            return null;
//        }
//
//        @Override
//        public void onProgressUpdate(Object... values) {
//            statusDialog.setMessage(values[0].toString());
//
//        }
//
//        @Override
//        public void onPostExecute(Object result) {
//            statusDialog.dismiss();
//        }
//    }
//
//
//    public class GMail {
//
//        final String emailPort = "587";// gmail's smtp port
//        final String smtpAuth = "true";
//        final String starttls = "true";
//        final String emailHost = "smtp.gmail.com";
//
//
//        String fromEmail;
//        String fromPassword;
//        List<String> toEmailList;
//        String emailSubject;
//        String emailBody;
//
//        Properties emailProperties;
////        Session mailSession;
////        MimeMessage emailMessage;
//
//        public GMail() {
//
//        }
//
//        public GMail(String fromEmail, String fromPassword,
//                     List<String> toEmailList, String emailSubject, String emailBody) {
//            this.fromEmail = fromEmail;
//            this.fromPassword = fromPassword;
//            this.toEmailList = toEmailList;
//            this.emailSubject = emailSubject;
//            this.emailBody = emailBody;
//
//            emailProperties = System.getProperties();
//            emailProperties.put("mail.smtp.port", emailPort);
//            emailProperties.put("mail.smtp.auth", smtpAuth);
//            emailProperties.put("mail.smtp.starttls.enable", starttls);
//            Log.i("GMail", "Mail server properties set.");
//        }
//
//        public MimeMessage createEmailMessage() throws AddressException,
//                MessagingException, UnsupportedEncodingException {
//
//            mailSession = Session.getDefaultInstance(emailProperties, null);
//            emailMessage = new MimeMessage(mailSession);
//
//            emailMessage.setFrom(new InternetAddress(fromEmail, fromEmail));
//            for (String toEmail : toEmailList) {
//                Log.i("GMail", "toEmail: " + toEmail);
//                emailMessage.addRecipient(Message.RecipientType.TO,
//                        new InternetAddress(toEmail));
//            }
//
//            emailMessage.setSubject(emailSubject);
//            emailMessage.setContent(emailBody, "text/html");// for a html email
//            // emailMessage.setText(emailBody);// for a text email
//            Log.i("GMail", "Email Message created.");
//            return emailMessage;
//        }
//
//        public void sendEmail() throws AddressException, MessagingException {
//
//            Transport transport = mailSession.getTransport("smtp");
//            transport.connect(emailHost, fromEmail, fromPassword);
//            Log.i("GMail", "allrecipients: " + emailMessage.getAllRecipients());
//            transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
//            transport.close();
//            Log.i("GMail", "Email sent successfully.");
//        }
//
//    }
//
//}
