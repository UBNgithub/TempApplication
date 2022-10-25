package com.mygeekbranch.tempapplication.dataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCity(City city);

    @Update
    void updateCity(City city);

    @Delete
    void deleteCity(City city);

    @Query("DELETE  FROM city WHERE id = :id")
    void deleteCityById(Long id);

    @Query("SELECT * FROM city")
    List<City> getAllCity();


}
