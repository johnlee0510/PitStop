package PitStop.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import PitStop.R;

public class ConfirmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar6);
        myToolbar.setTitleTextColor(android.graphics.Color.WHITE);
        setSupportActionBar(myToolbar);
    }
}
