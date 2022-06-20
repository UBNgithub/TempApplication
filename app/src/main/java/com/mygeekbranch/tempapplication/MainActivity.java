package com.mygeekbranch.tempapplication;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements Constants {
    private final static int REQUEST_CODE = 1;

    TextView dateText;
    TextView mTemperature;
    TextView mPasmurno;
    ActionBar actionBar;
    Button mAboutButton;
    Button mCityButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateText = findViewById(R.id.textViewDate);
        Date date = new Date();
        dateText.setText(date.toString());

        actionBar = getSupportActionBar();
        actionBar.setTitle("Kazan");

        int temp = Singleton.getSingleton().temperature;
        mTemperature = findViewById(R.id.temperatureTV);
        mTemperature.setText(Integer.toString(temp) + " °");
        mPasmurno = findViewById(R.id.weatherTV);
        mAboutButton = findViewById(R.id.aboutCityButton);
        mCityButton = findViewById(R.id.cityButton);
        mCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           Intent intent =new Intent(MainActivity.this, TwoActivity.class);
           intent.putExtra("fr", 2);
           startActivity(intent);

            }
        });

        mAboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = actionBar.getTitle().toString();
                String url = "https://ru.wikipedia.org/wiki/"+ city;

                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent intent = new Intent(MainActivity.this, TwoActivity.class);
                intent.putExtra("fr",1);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Clic setting", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.city:

                Intent intent1 = new Intent(MainActivity.this, TwoActivity.class);
                intent1.putExtra("fr",2);
                startActivity(intent1);


                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode != REQUEST_CODE) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        if (resultCode == RESULT_OK) {
            actionBar.setTitle(data.getStringExtra(RESULT));
        }
    }


    public void ClickItem5(MenuItem item) {
        Toast.makeText(MainActivity.this, "Вы выбрали 5- пункт меню", Toast.LENGTH_LONG).show();
    }


}