package com.example.diplom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        RadioButton male = findViewById(R.id.male);
        RadioButton female = findViewById(R.id.female);

        radioGroup.setOnCheckedChangeListener((group, checkedId) ->{

            if (checkedId == R.id.male){
                Toast.makeText(this, "male", Toast.LENGTH_SHORT).show();
            } else if (checkedId == R.id.female){
                Toast.makeText(this, "female", Toast.LENGTH_SHORT).show();
            }

        });


    }



}