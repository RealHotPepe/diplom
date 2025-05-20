package com.example.diplom;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        String fieldName = getIntent().getStringExtra("FIELD_NAME");
        String fieldTitle = getIntent().getStringExtra("FIELD_TITLE");

        TextView title = findViewById(R.id.chartTitle);
        title.setText("Динамика: " + fieldTitle);

        loadChartData(fieldName, fieldTitle);
        setupBackButton();
    }

    private void setupBackButton() {
        Button backButton = findViewById(R.id.backChart);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, DynamicsActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void loadChartData(String fieldName, String fieldTitle) {
        AppDatabase db = AppDatabase.getInstance(this);

        new Thread(() -> {
            List<Information> analyses = db.bloodAnalysisDao().getAllAnalysesSortedByDate();
            List<Entry> entries = new ArrayList<>();
            List<String> dates = new ArrayList<>();

            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            SimpleDateFormat displayFormat = new SimpleDateFormat("dd.MMM.yy", Locale.getDefault());

            // Собираем данные и сортируем их по дате
            for (int i = 0; i < analyses.size(); i++) {
                Information analysis = analyses.get(i);
                float value = getFieldValue(analysis, fieldName);
                try {
                    Date date = sdf.parse(analysis.getDate());
                    if (date != null) {
                        // Используем индекс как x-координату для правильного порядка точек
                        entries.add(new Entry(i, value));
                        dates.add(displayFormat.format(date));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            runOnUiThread(() -> setupChart(entries, dates, fieldTitle));
        }).start();
    }

    private void setupChart(List<Entry> entries, List<String> dates, String fieldTitle) {
        LineChart chart = findViewById(R.id.chart);

        if (entries.isEmpty()) {
            Toast.makeText(this, "Нет данных для отображения", Toast.LENGTH_SHORT).show();
            return;
        }

        // 1. Настройка набора данных
        LineDataSet dataSet = new LineDataSet(entries, fieldTitle);
        dataSet.setColor(Color.parseColor("#3F51B5"));
        dataSet.setCircleColor(Color.parseColor("#FF4081"));
        dataSet.setLineWidth(2f);
        dataSet.setCircleRadius(4f);
        dataSet.setValueTextSize(10f);
        dataSet.setDrawValues(true);
        dataSet.setMode(LineDataSet.Mode.LINEAR); // Важно: линейное соединение точек
        dataSet.setDrawFilled(false);

        // 2. Настройка оси X (даты)
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(dates));
        xAxis.setLabelCount(Math.min(dates.size(), 5)); // Оптимальное количество меток
        xAxis.setAvoidFirstLastClipping(true);

        // 3. Настройка оси Y (значения)
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setGranularity(1f);
        leftAxis.setAxisMinimum(0f);
        chart.getAxisRight().setEnabled(false);

        // 4. Дополнительные настройки графика
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setPinchZoom(true);
        chart.setDoubleTapToZoomEnabled(true);
        chart.getDescription().setEnabled(false);

        // 5. Установка данных
        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);

        // 6. Автомасштабирование и анимация
        chart.fitScreen();
        chart.animateY(1000, Easing.EaseInOutQuad);

        // 7. Обновление графика
        chart.invalidate();
    }

    private float getFieldValue(Information analysis, String fieldName) {
        switch (fieldName) {
            case "leik": return (float) analysis.getLeik();
            case "erit": return (float) analysis.getErit();
            case "gemo": return (float) analysis.getGemo();
            case "gemat": return (float) analysis.getGemat();
            case "SOE": return (float) analysis.getSOE();
            case "SSH": return (float) analysis.getSSH();
            case "SKH": return (float) analysis.getSKH();
            case "trombocit": return (float) analysis.getTrombocit();
            case "SOT": return (float) analysis.getSOT();
            case "trombokrit": return (float) analysis.getTrombokrit();
            case "IRT": return (float) analysis.getIRT();
            default: return 0;
        }
    }
}