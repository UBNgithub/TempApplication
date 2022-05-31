package com.mygeekbranch.tempapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CityActivity extends AppCompatActivity {
    TextView moscow;
    TextView cheboksary;
    TextView kazan;
    TextView nizhniy_novgorod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("City");
        moscow = findViewById(R.id.textViewMsc);
        moscow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}