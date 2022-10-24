package com.mygeekbranch.tempapplication.dataBase;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.room.Room;

public class App extends Application {
    private  static  App instance;
    private  CityDatabase db;
    private SharedPreferences citypreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        db = Room.databaseBuilder(
                getApplicationContext(),
                CityDatabase.class,
                "city_database")
                .allowMainThreadQueries() // Для того чтобы БД работала в основном потоке
                .build();

        citypreferences = getSharedPreferences("CitySP",0);

    }

    public static App getInstance() {
        return instance;
    }

    public CityDatabase getDb() {
        return db;
    }

    public SharedPreferences getCitypreferences() {
        return citypreferences;
    }
}
