package vivu.com.vivuapp;


import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import vivu.com.vivuapp.fragments.FunnyFragment;
import vivu.com.vivuapp.fragments.NewFeedFragment;
import vivu.com.vivuapp.fragments.SportsFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mBottomNav = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            switch (item.getItemId()){
                case R.id.newfeeds:
                    fragmentTransaction.replace(R.id.fragment_container, new NewFeedFragment()).commit();
                    return true;
                case R.id.funny:
                    fragmentTransaction.replace(R.id.fragment_container, new FunnyFragment()).commit();
                    return true;
                case R.id.sports:
                    fragmentTransaction.replace(R.id.fragment_container, new SportsFragment()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigationView = (BottomNavigationView)findViewById(R.id.bottom_nav_menu_id);
        navigationView.setOnNavigationItemSelectedListener(mBottomNav);

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new NewFeedFragment()).commit();
    }
}
