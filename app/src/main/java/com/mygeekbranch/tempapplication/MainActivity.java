package com.mygeekbranch.tempapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        //toolbar.setTitle(Singleton.getSingleton().getCurrentCity());

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_main, MainFragment.newInstance(null, null))
                    .commit();
        }


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView2);
        bottomNavigationView.setOnItemSelectedListener(itemSelectedListener2);


    }

    private NavigationBarView.OnItemSelectedListener itemSelectedListener2 =
            new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectfragment = null;
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            selectfragment = MainFragment.newInstance(null, null);
                            break;
                        case R.id.navigation_setting:
                            selectfragment = SettingFragment.newInstance(null, null);
                            break;
                        case R.id.navigation_city:
                            selectfragment = CityFragment.newInstance(null, null);
                            break;
                        case R.id.navigation_about:
                            selectfragment = AboutFragment.newInstance(null, null);
                            break;
                    }
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container_main, selectfragment)
                            .commit();
                    return true;
                }
            };


    @Override
    protected void onResume() {
        super.onResume();
        // toolbar.setTitle(Singleton.getSingleton().getCurrentCity());
    }
}