package com.example.diplom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView textAbout = findViewById(R.id.textAbout);
        textAbout.setText(R.string.textAbout);

        Button back = findViewById(R.id.back);
        back.setOnClickListener(v -> goBack());

    }

    private void goBack (){
        startActivity(new Intent(this, MainActivity.class));
    }

}