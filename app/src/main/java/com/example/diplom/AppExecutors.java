package com.example.diplom;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {
    private static AppExecutors instance;
    private final Executor diskIO;

    private AppExecutors(Executor diskIO) {
        this.diskIO = diskIO;
    }

    public static AppExecutors getInstance() {
        if (instance == null) {
            instance = new AppExecutors(Executors.newSingleThreadExecutor());
        }
        return instance;
    }

    public Executor diskIO() {
        return diskIO;
    }
}