package PitStop.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import PitStop.R;

public class QueueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.usermenu);
        setSupportActionBar(myToolbar);


        // Cancel Button
        Button mcancelButton = (Button) findViewById(R.id.cancelButton);
        mcancelButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                // Perform action on click

                Intent intent = new Intent(QueueActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                //setContentView(R.layout.activity_login);
            }
        });
    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.truckmenu, menu);

        return true;

    }



}
