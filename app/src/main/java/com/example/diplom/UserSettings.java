package com.example.diplom;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_settings")
public class UserSettings {
    @PrimaryKey
    public int id = 1; // Фиксированный ID для единственной записи

    @ColumnInfo(name = "gender")
    public String gender = "male"; // Значение по умолчанию
}