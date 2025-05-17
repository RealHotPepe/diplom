package com.example.diplom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class TestingActivity extends AppCompatActivity {

    private EditText editTextLeik, editTextErit, editTextGemo, editTextGemat,
            editTextSOE, editTextSSH, editTextSKH, editTextTrombocit,
            editTextSOT, editTextTrombokrit, editTextIRT;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        db = AppDatabase.getInstance(this);
        initViews();

        Button saveButton = findViewById(R.id.saveDB);
        saveButton.setOnClickListener(v -> saveButton());
    }

    private void initViews() {
        editTextLeik = findViewById(R.id.editLeik);
        editTextErit = findViewById(R.id.editErit);
        editTextGemo = findViewById(R.id.editGemo);
        editTextGemat = findViewById(R.id.editGemat);
        editTextSOE = findViewById(R.id.editSOE);
        editTextSSH = findViewById(R.id.editSSH);
        editTextSKH = findViewById(R.id.editSKH);
        editTextTrombocit = findViewById(R.id.editTrombocit);
        editTextSOT = findViewById(R.id.editSOT);
        editTextTrombokrit = findViewById(R.id.editTrombokrit);
        editTextIRT = findViewById(R.id.editIRT);
    }

    private void saveButton() {
        try {
            Information analysis = createAnalysisFromInput();

            new Thread(() -> {
                db.bloodAnalysisDao().insert(analysis);
                runOnUiThread(() -> {
                    Toast.makeText(this, "Данные сохранены", Toast.LENGTH_SHORT).show();
                    navigateToAnalysis(analysis);
                });
            }).start();

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Пожалуйста, заполните все поля корректными числами", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "Ошибка: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private Information createAnalysisFromInput() throws NumberFormatException {
        Information analysis = new Information();
        analysis.setDate(new Date());
        analysis.setLeik(Double.parseDouble(editTextLeik.getText().toString()));
        analysis.setErit(Double.parseDouble(editTextErit.getText().toString()));
        analysis.setGemo(Double.parseDouble(editTextGemo.getText().toString()));
        analysis.setGemat(Double.parseDouble(editTextGemat.getText().toString()));
        analysis.setSOE(Double.parseDouble(editTextSOE.getText().toString()));
        analysis.setSSH(Double.parseDouble(editTextSSH.getText().toString()));
        analysis.setSKH(Double.parseDouble(editTextSKH.getText().toString()));
        analysis.setTrombocit(Double.parseDouble(editTextTrombocit.getText().toString()));
        analysis.setSOT(Double.parseDouble(editTextSOT.getText().toString()));
        analysis.setTrombokrit(Double.parseDouble(editTextTrombokrit.getText().toString()));
        analysis.setIRT(Double.parseDouble(editTextIRT.getText().toString()));
        return analysis;
    }

    private void navigateToAnalysis(Information analysis) {
        Intent intent = new Intent(this, AnalysingActivity.class);
        intent.putExtra("analysis_data", analysis);
        startActivity(intent);
        finish();
    }

    public void goBack(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}