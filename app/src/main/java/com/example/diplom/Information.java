package com.example.diplom;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "info")
public class Information {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "leikocit")
    public double leik;
    @ColumnInfo(name = "eritrocit")
    public double erit;
    @ColumnInfo(name = "gemoglobin")
    public double gemo;
    @ColumnInfo(name = "gemotokrit")
    public double gemat;
    @ColumnInfo(name = "sqe")
    public double SOE;
    @ColumnInfo(name = "ssh")
    public double SSH;
    @ColumnInfo(name = "skh")
    public double SKH;
    @ColumnInfo(name = "trombocit")
    public double trombocit;
    @ColumnInfo(name = "sot")
    public double SOT;
    @ColumnInfo(name = "trombokrit")
    public double trombokrit;
    @ColumnInfo(name = "irt")
    public double IRT;


}