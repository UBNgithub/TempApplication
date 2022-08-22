package com.mygeekbranch.tempapplication;

import android.content.DialogInterface;
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

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.mygeekbranch.tempapplication.modelWeather.WeatherInit;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;

    private static MainActivity instance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;
        WeatherInit.Init(); // Запрос погоды с сервера


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
                            dialogFragment.show(getSupportFragmentManager(),"CityBottomSheetDialogFragment");


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

    private  OnDialogListener dialogListener = new OnDialogListener() {
        @Override
        public void onDialogset() {
            Toast.makeText(MainActivity.this, "Нажата SET", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onDialogCencel() {
            Toast.makeText(MainActivity.this, "Нажата CANCEL", Toast.LENGTH_LONG).show();
        }
    };
}