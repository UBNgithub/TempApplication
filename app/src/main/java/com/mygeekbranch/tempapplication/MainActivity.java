package com.mygeekbranch.tempapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.mygeekbranch.tempapplication.modelWeather.WeatherInit;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
 




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        WeatherInit.Init(MainActivity.context);


        toolbar = findViewById(R.id.toolbarMain);
        //toolbar.setTitle(Singleton.getSingleton().getCurrentCity());
        //setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.main);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Fragment selectfragment = null;
                switch (item.getItemId()) {

//                    case R.id.item1:
//                        selectfragment = SettingFragment.newInstance(null, null);
//                        break;
//                    case R.id.city:
//                        selectfragment = CityFragment.newInstance(null, null);
//                        break;
                    case R.id.about:
                        selectfragment = AboutFragment.newInstance(null, null);
                        break;
                }
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container_main, selectfragment)
                        .commit();
                return true;
            }
        });


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
//                        case R.id.navigation_about:
//                            selectfragment = AboutFragment.newInstance(null, null);
//                            break;
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

//    @Override
//    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }





}