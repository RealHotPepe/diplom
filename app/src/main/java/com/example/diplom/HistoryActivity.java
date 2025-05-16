package com.example.diplom;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private ListView listView;
    private AppDatabase db;
    private AnalysisAdapter adapter;
    private TextView tvGenderDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listView = findViewById(R.id.listView);
        tvGenderDisplay = findViewById(R.id.tvGenderDisplay);
        db = AppDatabase.getInstance(this);

        loadAnalyses();
        loadUserGender();

        // Обработка долгого нажатия
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            showDeleteDialog(position);
            return true;
        });
    }

    private void showDeleteDialog(int position) {
        new AlertDialog.Builder(this)
                .setTitle("Удаление записи")
                .setMessage("Вы уверены, что хотите удалить эту запись?")
                .setPositiveButton("Удалить", (dialog, which) -> {
                    adapter.removeItem(position);
                    Toast.makeText(this, "Запись удалена", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Отмена", null)
                .show();
    }

    private void loadAnalyses() {
        new AsyncTask<Void, Void, List<Information>>() {
            @Override
            protected List<Information> doInBackground(Void... voids) {
                return db.bloodAnalysisDao().getAllAnalyses();
            }

            @Override
            protected void onPostExecute(List<Information> analyses) {
                adapter = new AnalysisAdapter(HistoryActivity.this, analyses, db.bloodAnalysisDao());
                listView.setAdapter(adapter);
            }
        }.execute();
    }

    private void loadUserGender() {
        new Thread(() -> {
            UserSettings settings = db.userSettingsDao().getCurrentSettings();
            runOnUiThread(() -> {
                if (settings != null) {
                    String genderText = "Пол: " + ("male".equals(settings.gender) ? "мужской" : "женский");
                    tvGenderDisplay.setText(genderText);
                }
            });
        }).start();
    }

    public void goBack(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}