package com.example.diplom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class TestingActivity extends AppCompatActivity {

    EditText editTextLeik = findViewById(R.id.editLeik);
    EditText editTextErit = findViewById(R.id.editErit);
    EditText editTextGemo = findViewById(R.id.editGemo);
    EditText editTextGemat = findViewById(R.id.editGemat);
    EditText editTextSOE = findViewById(R.id.editSOE);
    EditText editTextSSH = findViewById(R.id.editSSH);
    EditText editTextSKH = findViewById(R.id.editSKH);
    EditText editTextTrombocit = findViewById(R.id.editTrombocit);
    EditText editTextSOT = findViewById(R.id.editSOT);
    EditText editTextTrombokrit = findViewById(R.id.editTrombokrit);
    EditText editTextIRT = findViewById(R.id.editIRT);
    AppDatabase db = Room.databaseBuilder(getApplicationContext(),
            AppDatabase.class, "blood-analysis-database").allowMainThreadQueries().build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        Button saveButton = findViewById(R.id.saveDB);
        saveButton.setOnClickListener(v -> saveButton());

    }


    private void saveButton (){

        double leik = Double.parseDouble(editTextLeik.getText().toString());
        double erit = Double.parseDouble(editTextErit.getText().toString());
        double gemo = Double.parseDouble(editTextGemo.getText().toString());
        double gemat = Double.parseDouble(editTextGemat.getText().toString());
        double SOE = Double.parseDouble(editTextSOE.getText().toString());
        double SSH = Double.parseDouble(editTextSSH.getText().toString());
        double SKH = Double.parseDouble(editTextSKH.getText().toString());
        double trombocit = Double.parseDouble(editTextTrombocit.getText().toString());
        double SOT = Double.parseDouble(editTextSOT.getText().toString());
        double trombokrit = Double.parseDouble(editTextTrombokrit.getText().toString());
        double IRT = Double.parseDouble(editTextIRT.getText().toString());

        Information analysis = new Information();

        analysis.setDate(new Date());
        analysis.setLeik(leik);
        analysis.setErit(erit);
        analysis.setGemo(gemo);
        analysis.setGemat(gemat);
        analysis.setSOE(SOE);
        analysis.setSSH(SSH);
        analysis.setSKH(SKH);
        analysis.setTrombocit(trombocit);
        analysis.setSOT(SOT);
        analysis.setTrombokrit(trombokrit);
        analysis.setIRT(IRT);

        new Thread(() ->{
            db.bloodAnalysisDao().insert(analysis);
            runOnUiThread(() -> {
                Toast.makeText(this, "Данные сохранены", Toast.LENGTH_SHORT).show();
            });
        }).start();

    }

    public void goBack (View view){
        startActivity(new Intent(this, MainActivity.class));
    }

}