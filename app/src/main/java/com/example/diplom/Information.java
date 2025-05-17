package com.example.diplom;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@Entity(tableName = "blood_analysis")
public class Information implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "date")
    public String date;
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

    public String getDate() {
        return date;
    }

    public void setDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        sdf.setTimeZone(TimeZone.getDefault());
        this.date = sdf.format(date);
    }

    public double getLeik() {
        return leik;
    }

    public void setLeik(double leik) {
        this.leik = leik;
    }

    public double getErit() {
        return erit;
    }

    public void setErit(double erit) {
        this.erit = erit;
    }

    public double getGemo() {
        return gemo;
    }

    public void setGemo(double gemo) {
        this.gemo = gemo;
    }

    public double getGemat() {
        return gemat;
    }

    public void setGemat(double gemat) {
        this.gemat = gemat;
    }

    public double getSOE() {
        return SOE;
    }

    public void setSOE(double SOE) {
        this.SOE = SOE;
    }

    public double getSSH() {
        return SSH;
    }

    public void setSSH(double SSH) {
        this.SSH = SSH;
    }

    public double getSKH() {
        return SKH;
    }

    public void setSKH(double SKH) {
        this.SKH = SKH;
    }

    public double getTrombocit() {
        return trombocit;
    }

    public void setTrombocit(double trombocit) {
        this.trombocit = trombocit;
    }

    public double getSOT() {
        return SOT;
    }

    public void setSOT(double SOT) {
        this.SOT = SOT;
    }

    public double getTrombokrit() {
        return trombokrit;
    }

    public void setTrombokrit(double trombokrit) {
        this.trombokrit = trombokrit;
    }

    public double getIRT() {
        return IRT;
    }

    public void setIRT(double IRT) {
        this.IRT = IRT;
    }

}