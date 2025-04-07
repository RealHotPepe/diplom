package com.example.diplom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void goAbout (View view){
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
    public void goTesting (View view){
        Intent intent = new Intent(this, TestingActivity.class);
        startActivity(intent);
    }
}