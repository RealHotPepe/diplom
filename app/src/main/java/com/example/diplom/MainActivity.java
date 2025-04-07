package com.example.diplom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button about = findViewById(R.id.about);
        Button testing = findViewById(R.id.testing);

        about.setOnClickListener(v -> goAbout());
        testing.setOnClickListener(v -> goTesting());

    }

    private void goAbout (){
        startActivity(new Intent(this, AboutActivity.class));
    }
    private void goTesting (){
        startActivity(new Intent(this, TestingActivity.class));
    }
}