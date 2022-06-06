package com.mygeekbranch.tempapplication;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity   implements Constants  {
    private final static int REQUEST_CODE = 1;
    TextView dateText;
    TextView mTemperature;
    TextView mPasmurno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateText = findViewById(R.id.textViewDate);
        Date date = new Date();
        dateText.setText(date.toString());

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Moscow");

        int temp = Singleton.getSingleton().temperature;
        mTemperature = findViewById(R.id.temperatureTV);
        mTemperature.setText(Integer.toString(temp) + " °");
        mPasmurno = findViewById(R.id.weatherTV);

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
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Clic setting", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.city:
                Intent intent1  = new Intent(MainActivity.this, CityActivity.class);
                //startActivity(intent1);
                startActivityForResult(intent1,REQUEST_CODE);

                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode != REQUEST_CODE){
            super.onActivityResult(requestCode, resultCode, data);
        return;}
        if (resultCode == RESULT_OK) {

            mPasmurno.setText(data.getStringExtra(RESULT));
        }
    }


    public  void ClickItem5(MenuItem item){
        Toast.makeText(MainActivity.this,"Вы выбрали 5- пункт меню", Toast.LENGTH_LONG).show();
    }


}