package vivu.com.vivuapp;


import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import vivu.com.vivuapp.adapter.ViewPagerAdapter;
import vivu.com.vivuapp.fragments.FunnyFragment;
import vivu.com.vivuapp.fragments.NewFeedFragment;
import vivu.com.vivuapp.fragments.SportsFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    BottomNavigationView mBottomNavigationView;

    //fragment
    NewFeedFragment newFeedFragment;
    FunnyFragment funnyFragment;
    SportsFragment sportsFragment;
    MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.fragment_container);
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav_menu_id);

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.newfeeds:
                        mViewPager.setCurrentItem(0);
                        return true;
                    case R.id.funny:
                        mViewPager.setCurrentItem(1);
                        return true;
                    case R.id.sports:
                        mViewPager.setCurrentItem(2);
                        return true;
                    default:
                }
                return false;
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null){
                    menuItem.setChecked(false);
                }else {
                    mBottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                mBottomNavigationView.getMenu().getItem(position).setChecked(true);
                menuItem = mBottomNavigationView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setupViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        newFeedFragment = new NewFeedFragment();
        funnyFragment = new FunnyFragment();
        sportsFragment = new SportsFragment();
        viewPagerAdapter.addFragment(newFeedFragment);
        viewPagerAdapter.addFragment(funnyFragment);
        viewPagerAdapter.addFragment(sportsFragment);
        viewPager.setAdapter(viewPagerAdapter);
    }
}
