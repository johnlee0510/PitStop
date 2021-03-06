package PitStop.Controller;
/**
 * Created by John on 10/28/2017.
 */
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.Serializable;

import PitStop.Model.User;
import PitStop.R;

public class MainUserActivity extends AppCompatActivity implements CurrentTab.OnFragmentInteractionListener ,WeeklyTab.OnFragmentInteractionListener {
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.usermenu);
        myToolbar.setTitleTextColor(android.graphics.Color.WHITE);
        setSupportActionBar(myToolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Current"));
        tabLayout.addTab(tabLayout.newTab().setText("Weekly"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

//        try {
//            Bundle extras = getIntent().getExtras();
//            if (extras != null) {
//                user = (User) getIntent().getSerializableExtra("user"); //Obtaining data
//            }
//        } catch (Exception e) {
//            Log.d("debug", "something went wrong");
//        }

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

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
                Intent intent =  new Intent(getApplicationContext(), ProfileActivity.class);
                intent.putExtra("user", (Serializable) user);
                startActivity(intent);
                return true;

            case R.id.action_order:
                // User chose the "Logout" action, mark the current item
                this.startActivity(new Intent(MainUserActivity.this, OrderActivity.class));
                return true;

            case R.id.action_logout:
                // User chose the "Logout" action, mark the current item
                Toast.makeText(getApplicationContext(), "Sign off, good bye", Toast.LENGTH_SHORT).show();
                this.startActivity(new Intent(MainUserActivity.this, WelcomeScreen.class));
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
