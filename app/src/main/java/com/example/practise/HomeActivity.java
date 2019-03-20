package com.example.practise;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class HomeActivity extends AppCompatActivity {
    private BottomNavigationView mainNav;
    private FrameLayout mainFrame;
    private HomeFragment homeFragment;
    private OrderFragment orderFragment;
    private AccountFragment accountFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mainFrame=(FrameLayout)findViewById(R.id.main_frame);
        mainNav=(BottomNavigationView)findViewById(R.id.main_nav);
        homeFragment = new HomeFragment();
        orderFragment = new OrderFragment();
        accountFragment = new AccountFragment();

        mainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_home:
                        setFragment(homeFragment);
                        return true;
                    case R.id.nav_order:
                        setFragment(orderFragment);
                        return true;
                    case R.id.nav_account:
                        setFragment(accountFragment);
                        return true;
                    default:
                        return false;

                }


            }

            private void setFragment(Fragment fragment) {

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_frame,fragment);
                fragmentTransaction.commit();

            }
        });
    }
}
