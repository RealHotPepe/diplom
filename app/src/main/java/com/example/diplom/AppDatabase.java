package com.example.diplom;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.diplom.Information;

@Database(entities = {Information.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract InformationDAO bloodAnalysisDao();
}