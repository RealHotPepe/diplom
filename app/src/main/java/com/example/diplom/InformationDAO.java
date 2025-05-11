package com.example.diplom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface InformationDAO {

    @Query("SELECT * FROM blood_analysis")
    List<Information> getAll();

    @Query("SELECT * FROM blood_analysis WHERE id = :infoId")
    Information getById (int infoId);

    @Insert
    void insert (Information information);

    @Update
    void updating (Information information);

    @Delete
    void delete (Information information);

}
