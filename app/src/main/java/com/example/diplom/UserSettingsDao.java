package com.example.diplom;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface UserSettingsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSettings(UserSettings settings);

    @Query("SELECT * FROM user_settings WHERE id = 1 LIMIT 1")
    UserSettings getCurrentSettings();

    @Query("UPDATE user_settings SET gender = :gender WHERE id = 1")
    void updateGender(String gender);
}