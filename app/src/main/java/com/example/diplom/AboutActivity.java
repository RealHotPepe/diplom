package com.example.diplom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView textAbout = findViewById(R.id.textAbout);

        textAbout.setText(R.string.textAbout);

    }

    public void goBack (View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}