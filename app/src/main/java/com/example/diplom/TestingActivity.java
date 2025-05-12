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

    private EditText editTextLeik, editTextErit, editTextGemo, editTextGemat,
            editTextSOE, editTextSSH, editTextSKH, editTextTrombocit,
            editTextSOT, editTextTrombokrit, editTextIRT;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        // Инициализация базы данных
        db = AppDatabase.getInstance(this);

        // Инициализация полей ввода ПОСЛЕ setContentView()
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

            new Thread(() -> {
                db.bloodAnalysisDao().insert(analysis);
                runOnUiThread(() -> {
                    Toast.makeText(this, "Данные сохранены", Toast.LENGTH_SHORT).show();
                    clearForm();
                });
            }).start();

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Пожалуйста, заполните все поля корректными числами", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "Ошибка: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void clearForm() {
        editTextLeik.setText("");
        editTextErit.setText("");
        editTextGemo.setText("");
        editTextGemat.setText("");
        editTextSOE.setText("");
        editTextSSH.setText("");
        editTextSKH.setText("");
        editTextTrombocit.setText("");
        editTextSOT.setText("");
        editTextTrombokrit.setText("");
        editTextIRT.setText("");
    }

    public void goBack(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish(); // Закрываем текущую активити
    }
}