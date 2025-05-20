package com.example.diplom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button about = findViewById(R.id.about);
        Button testing = findViewById(R.id.testing);
        Button settings = findViewById(R.id.settings);
        Button history = findViewById(R.id.history);
        Button dynamics = findViewById(R.id.dynamics);

        about.setOnClickListener(v -> goAbout());
        testing.setOnClickListener(v -> goTesting());
        settings.setOnClickListener(v -> goSettings());
        history.setOnClickListener(v -> goHistory());
        dynamics.setOnClickListener(v -> goDynamics());

    }

    private void goAbout (){
        startActivity(new Intent(this, AboutActivity.class));
    }
    private void goTesting (){
        startActivity(new Intent(this, TestingActivity.class));
    }
    private void goSettings (){
        startActivity(new Intent(this, SettingsActivity.class));
    }
    private void goHistory (){
        startActivity(new Intent(this, HistoryActivity.class));
    }
    private void goDynamics (){
        startActivity(new Intent(this, DynamicsActivity.class));
    }
}