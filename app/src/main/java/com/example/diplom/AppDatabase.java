package com.example.diplom;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Information.class, UserSettings.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                    .addMigrations(MIGRATION_1_2)
                    .build();
        }
        return instance;
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS user_settings " +
                    "(id INTEGER PRIMARY KEY NOT NULL, gender TEXT NOT NULL)");
            database.execSQL("INSERT INTO user_settings (id, gender) VALUES (1, 'male')");
        }
    };

    public abstract InformationDAO bloodAnalysisDao();
    public abstract UserSettingsDao userSettingsDao();
}