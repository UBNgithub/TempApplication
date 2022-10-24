package com.mygeekbranch.tempapplication.dataBase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class City {
    @PrimaryKey (autoGenerate = true)
    public long id;
    @ColumnInfo (name = "city")
    public  String city;

}
