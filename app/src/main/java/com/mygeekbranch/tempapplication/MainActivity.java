package com.mygeekbranch.tempapplication;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Constants {
    private final static int REQUEST_CODE = 1;

    TextView dateText;
    TextView mTemperature;
    TextView mPasmurno;
    ActionBar actionBar;


    List< WeekWeatherModel> weekList;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dateText = findViewById(R.id.textViewDate);
        Date date = new Date();
        dateText.setText(date.toString());

        actionBar = getSupportActionBar();
       // actionBar.setTitle("Kazan");
        //actionBar.setTitle(Singleton.getSingleton().getCurrentCity());

        int temp = Singleton.getSingleton().temperature;
        mTemperature = findViewById(R.id.temperatureTV);
        mTemperature.setText(Integer.toString(temp) + " °");
        mPasmurno = findViewById(R.id.weatherTV);

        initListWeekWeather();
        initRecyclerView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        actionBar.setTitle(Singleton.getSingleton().getCurrentCity());

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
    private   void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.week_recyclerView);
        recyclerView.setHasFixedSize(true);
        //Декоратор
        DividerItemDecoration decoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        decoration.setDrawable(getDrawable(R.drawable.item_separator));
        recyclerView.addItemDecoration(decoration);


        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        WeekAdapter adapter = new WeekAdapter(weekList);
        recyclerView.setAdapter(adapter);
    }
    private  void initListWeekWeather(){
        weekList = new ArrayList<>();
        weekList.add(new WeekWeatherModel("ПН", "+9°"));
        weekList.add(new WeekWeatherModel("ВТ", "+5°"));
        weekList.add(new WeekWeatherModel("СР", "+4°"));
        weekList.add(new WeekWeatherModel("ЧТ", "+10°"));
        weekList.add(new WeekWeatherModel("ПT", "+12°"));
        weekList.add(new WeekWeatherModel("СБ", "+18°"));
        weekList.add(new WeekWeatherModel("ВС", "+20°"));
    }





}