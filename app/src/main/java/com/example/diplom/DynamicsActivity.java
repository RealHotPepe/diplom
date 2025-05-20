package com.example.diplom;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Locale;

public class DynamicsActivity extends AppCompatActivity {

    private LinearLayout container;
    private AppDatabase db;
    private String userGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamics);

        container = findViewById(R.id.dynamicsContainer);
        db = AppDatabase.getInstance(this);

        setupBackButton();
        loadUserGender();
    }

    private void setupBackButton() {
        Button backButton = findViewById(R.id.backDynamic);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(DynamicsActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void loadUserGender() {
        new Thread(() -> {
            UserSettings settings = db.userSettingsDao().getCurrentSettings();
            userGender = settings != null ? settings.gender : "male";
            runOnUiThread(this::displayAllReferenceValues);
        }).start();
    }

    private void displayAllReferenceValues() {
        container.removeAllViews();

        // Общий анализ крови
        addReferenceItem("Лейкоциты (WBC)", "leik");
        addReferenceItem("Эритроциты (RBC)", "erit");
        addReferenceItem("Гемоглобин (HGB)", "gemo");
        addReferenceItem("Гематокрит (HCT)", "gemat");
        addReferenceItem("Ср. объем эритроцитов (MCV)", "SOE");
        addReferenceItem("Ср. содержание Hb (MCH)", "SSH");
        addReferenceItem("Ср. конц. Hb (MCHC)", "SKH");
        addReferenceItem("Тромбоциты (PLT)", "trombocit");
        addReferenceItem("Ср. объем тромбоцитов (MPV)", "SOT");
        addReferenceItem("Тромбокрит (PCT)", "trombokrit");
        addReferenceItem("Индекс распред. тромбоцитов (PDW)", "IRT");
    }

    private void addReferenceItem(String name, String dbFieldName) {
        TableRow row = new TableRow(this);
        row.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        row.setPadding(10, 18, 10, 10);

        TextView nameView = new TextView(this);
        nameView.setLayoutParams(new TableRow.LayoutParams(
                0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
        nameView.setText(name);
        nameView.setTextSize(24);
        nameView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        nameView.setPadding(5, 5, 5, 5);
        nameView.setBackgroundResource(R.drawable.rounded_clickable_item);

        // Устанавливаем обработчик нажатия
        nameView.setOnClickListener(v -> {
            Intent intent = new Intent(DynamicsActivity.this, ChartActivity.class);
            intent.putExtra("FIELD_NAME", dbFieldName);
            intent.putExtra("FIELD_TITLE", name);
            startActivity(intent);
        });

        row.addView(nameView);
        container.addView(row);
    }

    public void goBack(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}