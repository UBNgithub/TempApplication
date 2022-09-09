package com.mygeekbranch.tempapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.mygeekbranch.tempapplication.modelWeather.GetUrlData;
import com.mygeekbranch.tempapplication.modelWeather.GetWeatherData;
import com.mygeekbranch.tempapplication.modelWeather.GetWeatherRetrofit;
import com.mygeekbranch.tempapplication.modelWeather.GetWeatherService;
import com.mygeekbranch.tempapplication.modelWeather.GetWeatherWorker;
import com.mygeekbranch.tempapplication.modelWeather.OkHttpRequest;
import com.mygeekbranch.tempapplication.modelWeather.WeatherInit;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;

    private static MainActivity instance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;
        initNotificationChannel();

        // 1-й способ. Запрос погоды с сервера
        //WeatherInit.Init();

        // 2-й способ. Запрос погоды с сервера из отдельного класса
        // ипользуется 2 отдельных класса:
        // 1-й GetUrlData -получение данных из сервера
        // 2-й GetWeatherData - обработка данных
        //GetUrlData.Init();

        // 3-й способ. Запрос с сервера через Workmanager
        // WeatherInit();

        // 4-й способ. Запрос с сервера через Service
        //startService(new Intent(MainActivity.this, GetWeatherService.class));

        // 5-й способ. через OkHttp
        //OkHttpRequest.run("https://api.openweathermap.org/data/2.5/weather?q=Cheboksary,RU&units=metric&appid=f61adcb6ab99fd0e42d9728a4eea3df7");

        // 6-й способ. через Retrofit
         GetWeatherRetrofit.initRetrofit();



        toolbar = findViewById(R.id.toolbarMain);
        //toolbar.setTitle(Singleton.getSingleton().getCurrentCity());
        setSupportActionBar(toolbar);
        //toolbar.inflateMenu(R.menu.main);
        initDrawer(toolbar);


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


    private void initDrawer(Toolbar toolbar) {// метод драйвера
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


    }

    // lisener для нижней навигационной панели
    private NavigationBarView.OnItemSelectedListener itemSelectedListener2 =
            new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectfragment = null;
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            selectfragment = MainFragment.newInstance(null, null);

                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.fragment_container_main, selectfragment)
                                    .commit();
                            break;
                        case R.id.navigation_setting:
                            selectfragment = SettingFragment.newInstance(null, null);
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.fragment_container_main, selectfragment)
                                    .commit();
                            break;
                        case R.id.navigation_city:

//                            CityDialogFragment cityDialogFragment = new CityDialogFragment();
//                            cityDialogFragment.show(getSupportFragmentManager(), "cityDialogFragment");
                            CityBottomSheetDialogFragment dialogFragment = CityBottomSheetDialogFragment.newInstance();
                            dialogFragment.show(getSupportFragmentManager(), "CityBottomSheetDialogFragment");


                            break;
                        case R.id.navigation_about:
                            selectfragment = AboutFragment.newInstance(null, null);
                            break;
                    }

//                    getSupportFragmentManager()
//                            .beginTransaction()
//                            .replace(R.id.fragment_container_main, selectfragment)
//                            .commit();
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
    // Метод для получения экземпляра MainActivity Другим классом
    public static MainActivity getInstance() {
        return instance;
    }

    public void initMaimFragment() {
        //Toast.makeText(MainActivity.this, "Mainactyivity Toast Request weathher finish", Toast.LENGTH_SHORT).show();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_main, MainFragment.newInstance(null, null))
                .commit();

    }

    public void showError() {
        //Toast.makeText(MainActivity.this, "Ошибка соединения", Toast.LENGTH_LONG).show();
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Ошибка соединения с сервером")
                .setIcon(R.drawable.icon)
                .setCancelable(false)
                .setNegativeButton("Закрыть окно",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "Диалог закрыт", Toast.LENGTH_SHORT).show();
                            }
                        })
                .setPositiveButton("Обновить",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                WeatherInit.Init();

                            }
                        })
                .create()
                .show();


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Fragment selectfragment = null;
        if (id == R.id.navigation_home) {
            selectfragment = MainFragment.newInstance(null, null);

        } else if (id == R.id.navigation_city) {
            selectfragment = CityFragment.newInstance(null, null);

        } else if (id == R.id.navigation_setting) {
            selectfragment = SettingFragment.newInstance(null, null);

        } else if (id == R.id.navigation_about) {
            selectfragment = AboutFragment.newInstance(null, null);

        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_main, selectfragment)
                .commit();
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);


        return true;
    }

    private OnDialogListener dialogListener = new OnDialogListener() {
        @Override
        public void onDialogset() {
            Toast.makeText(MainActivity.this, "Нажата SET", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onDialogCencel() {
            Toast.makeText(MainActivity.this, "Нажата CANCEL", Toast.LENGTH_LONG).show();
        }
    };

    public void WeatherInit() {
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest
                .Builder(GetWeatherWorker.class)
                // .setInitialDelay(5,TimeUnit.SECONDS)
                .build();
        WorkManager workManager = WorkManager.getInstance(MainActivity.this);
        workManager.enqueue(workRequest);
    }

    // На Андроидах версии 26 и выше необходимо создавать канал нотификации
    // На старых версиях канал создавать не надо
    private void initNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            int impotance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel mChannal = new NotificationChannel("2", "name", impotance);
            notificationManager.createNotificationChannel(mChannal);
        }
    }
}