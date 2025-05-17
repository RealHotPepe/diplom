package com.example.diplom;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class AnalysingActivity extends AppCompatActivity {

    private LinearLayout resultsContainer;
    private AppDatabase db;
    private String userGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysing);

        resultsContainer = findViewById(R.id.resultsContainer);
        db = AppDatabase.getInstance(this);

        loadUserGender();

        Information analysis = (Information) getIntent().getSerializableExtra("analysis_data");
        if (analysis != null) {
            displayAnalysisResults(analysis);
        } else {
            Toast.makeText(this, "Данные анализа не получены", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void loadUserGender() {
        new Thread(() -> {
            UserSettings settings = db.userSettingsDao().getCurrentSettings();
            userGender = settings != null ? settings.gender : "male";
        }).start();
    }

    private void displayAnalysisResults(Information analysis) {
        resultsContainer.removeAllViews();

        // Анализ всех показателей крови
        analyzeLeukocytes(analysis.getLeik());
        analyzeErythrocytes(analysis.getErit());
        analyzeHemoglobin(analysis.getGemo());
        analyzeHematocrit(analysis.getGemat());
        analyzeMCV(analysis.getSOE());
        analyzeMCH(analysis.getSSH());
        analyzeMCHC(analysis.getSKH());
        analyzePlatelets(analysis.getTrombocit());
        analyzeMPV(analysis.getSOT());
        analyzePCT(analysis.getTrombokrit());
        analyzePDW(analysis.getIRT());
    }

    private void analyzeLeukocytes(double value) {
        double[] ranges = "female".equals(userGender)
                ? new double[]{4.31, 11.00}
                : new double[]{3.89, 9.23};

        addResultRow("Лейкоциты", value, ranges,
                "Повышение (лейкоцитоз): инфекции, воспаления, лейкозы\n" +
                        "Понижение (лейкопения): вирусные инфекции, аутоиммунные заболевания");
    }

    private void analyzeErythrocytes(double value) {
        double[] ranges = "female".equals(userGender)
                ? new double[]{3.96, 5.03}
                : new double[]{3.66, 4.76};

        addResultRow("Эритроциты", value, ranges,
                "Повышение (эритроцитоз): обезвоживание, гипоксия\n" +
                        "Понижение (анемия): кровопотери, дефицит железа");
    }

    private void analyzeHemoglobin(double value) {
        double[] ranges = "female".equals(userGender)
                ? new double[]{107.00, 134.00}
                : new double[]{118.50, 142.00};

        addResultRow("Гемоглобин", value, ranges,
                "Повышение: обезвоживание, курение\n" +
                        "Понижение: анемии, кровопотери");
    }

    private void analyzeHematocrit(double value) {
        double[] ranges = "female".equals(userGender)
                ? new double[]{32.20, 39.80}
                : new double[]{34.26, 43.45};

        addResultRow("Гематокрит", value, ranges,
                "Повышение: эритремия, обезвоживание\n" +
                        "Понижение: анемии, гипергидратация");
    }

    private void analyzeMCV(double value) {
        double[] ranges = "female".equals(userGender)
                ? new double[]{74.40, 86.10}
                : new double[]{86.50, 101.79};

        addResultRow("Ср. объем эр.", value, ranges,
                "Повышение: B12/фолат-дефицит\n" +
                        "Понижение: железодефицит, талассемия");
    }

    private void analyzeMCH(double value) {
        double[] ranges = "female".equals(userGender)
                ? new double[]{24.90, 29.20}
                : new double[]{27.23, 33.60};

        addResultRow("Ср. содерж. Hb", value, ranges,
                "Повышение: гиперхромные анемии\n" +
                        "Понижение: гипохромные анемии");
    }

    private void analyzeMCHC(double value) {
        double[] ranges = "female".equals(userGender)
                ? new double[]{322.00, 349.00}
                : new double[]{305.90, 337.60};

        addResultRow("Ср. конц. Hb", value, ranges,
                "Повышение: сфероцитоз\n" +
                        "Понижение: железодефицит");
    }

    private void analyzePlatelets(double value) {
        double[] ranges = "female".equals(userGender)
                ? new double[]{206.00, 360.00}
                : new double[]{157.80, 388.00};

        addResultRow("Тромбоциты", value, ranges,
                "Повышение: воспаления, опухоли\n" +
                        "Понижение: кровотечения, лейкозы");
    }

    private void analyzeMPV(double value) {
        double[] ranges = "female".equals(userGender)
                ? new double[]{9.20, 11.40}
                : new double[]{8.10, 12.60};

        addResultRow("Ср. объем тр.", value, ranges,
                "Повышение: тромбоцитопении\n" +
                        "Понижение: наследственные патологии");
    }

    private void analyzePCT(double value) {
        double[] ranges = "female".equals(userGender)
                ? new double[]{0.12, 0.35}
                : new double[]{0.21, 0.39};

        addResultRow("Тромбокрит", value, ranges,
                "Повышение: миелопролиферация\n" +
                        "Понижение: тромбоцитопении");
    }

    private void analyzePDW(double value) {
        double[] ranges = new double[]{9.30, 16.70};

        addResultRow("Распред. тр.", value, ranges,
                "Повышение: воспалительные процессы\n" +
                        "Понижение: норма или патология");
    }

    private void addResultRow(String name, double value, double[] ranges, String interpretation) {
        TableRow row = new TableRow(this);
        row.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        row.setPadding(0, 16, 0, 16);

        // Колонка 1: Название показателя
        TextView nameView = new TextView(this);
        nameView.setLayoutParams(new TableRow.LayoutParams(
                0, TableRow.LayoutParams.WRAP_CONTENT, 1.5f));
        nameView.setText(name);
        nameView.setTextSize(16);
        nameView.setGravity(Gravity.START);
        nameView.setPadding(16, 0, 0, 0);

        // Колонка 2: Значение пользователя
        TextView valueView = new TextView(this);
        valueView.setLayoutParams(new TableRow.LayoutParams(
                0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
        valueView.setText(String.format(Locale.getDefault(), "%.2f %s", value, ""));
        valueView.setTextSize(16);
        valueView.setGravity(Gravity.CENTER);

        // Колонка 3: Референсные значения
        TextView rangeView = new TextView(this);
        rangeView.setLayoutParams(new TableRow.LayoutParams(
                0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
        rangeView.setText(String.format(Locale.getDefault(), "%.2f-%.2f", ranges[0], ranges[1]));
        rangeView.setTextSize(16);
        rangeView.setGravity(Gravity.CENTER);

        // Оформление отклонений
        if (value < ranges[0] || value > ranges[1]) {
            int color = value < ranges[0] ? Color.parseColor("#8B0000") : Color.parseColor("#FF6347");
            valueView.setTextColor(color);
            valueView.setTypeface(null, Typeface.BOLD);
            valueView.setOnClickListener(v -> showReferenceDialog(name, interpretation));
            valueView.setClickable(true);
            valueView.setBackgroundResource(R.drawable.rounded_clickable_item);
        }

        row.addView(nameView);
        row.addView(valueView);
        row.addView(rangeView);
        resultsContainer.addView(row);

        // Добавляем разделитель
        View divider = new View(this);
        divider.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                1));
        divider.setBackgroundColor(Color.parseColor("#EEEEEE"));
        resultsContainer.addView(divider);
    }

    private void showReferenceDialog(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle("Интерпретация: " + title)
                .setMessage(message)
                .setPositiveButton("Закрыть", null)
                .show();
    }

    public void goBack(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
    public void goHistory(View view) {
        startActivity(new Intent(this, HistoryActivity.class));
        finish();
    }
}