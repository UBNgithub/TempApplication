package com.mygeekbranch.tempapplication;

import androidx.annotation.NonNull;

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

public class MainActivity extends AppCompatActivity {
    TextView dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dateText = findViewById(R.id.textViewDate);
        Date date = new Date();
        dateText.setText(date.toString());

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Moscow");
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
                startActivity(intent1);
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public  void ClickItem5(MenuItem item){
        Toast.makeText(MainActivity.this,"Вы выбрали 5- пункт меню", Toast.LENGTH_LONG).show();
    }


}