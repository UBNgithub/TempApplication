package com.mygeekbranch.tempapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
// Временое активити для переноса перехода приложения на фрагменты
public class TwoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        int numberFragment = getIntent().getExtras().getInt("fr");
        if ( numberFragment == 2) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, CityFragment.newInstance(null, null))
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, SettingFragment.newInstance(null, null))
                    .commit();
        }

    }
}