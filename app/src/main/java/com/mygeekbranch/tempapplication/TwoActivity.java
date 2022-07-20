package com.mygeekbranch.tempapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
// Временое активити для переноса перехода приложения на фрагменты
public class TwoActivity extends AppCompatActivity {
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Activity 2");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        int numberFragment = getIntent().getExtras().getInt("fr");
        if ( numberFragment == 2) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, CityFragment.newInstance(null, null))
                    .commit();

        } else
        if ( numberFragment == 3) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, AboutFragment.newInstance(null, null))
                    .commit();

        }
        else
        if ( numberFragment == 4) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, MainFragment.newInstance(null, null))
                    .commit();

        }
        else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, SettingFragment.newInstance(null, null))
                    .commit();
        }

    }
}