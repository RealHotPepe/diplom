package com.example.diplom;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HistoryActivity extends AppCompatActivity {
    private ListView listView;
    private AppDatabase db;
    private ArrayAdapter<String> adapter;
    private List<String> analysisDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listView = findViewById(R.id.listView);
        analysisDataList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                analysisDataList);
        listView.setAdapter(adapter);

        // Используем синглтон
        db = AppDatabase.getInstance(this);

        loadAnalyses();
    }

    private void loadAnalyses() {
        new AsyncTask<Void, Void, List<Information>>() {
            @Override
            protected List<Information> doInBackground(Void... voids) {
                return db.bloodAnalysisDao().getAllAnalyses();
            }

            @Override
            protected void onPostExecute(List<Information> analyses) {
                analysisDataList.clear();

                if (analyses == null || analyses.isEmpty()) {
                    analysisDataList.add("Нет данных об анализах");
                } else {
                    for (Information analysis : analyses) {
                        String analysisText = String.format(Locale.getDefault(),
                                "Дата: %s\n" +
                                        "Лейкоциты: %.2f\n" +
                                        "Эритроциты: %.2f\n" +
                                        "Гемоглобин: %.2f\n" +
                                        "Гематокрит: %.2f\n" +
                                        "СОЭ: %.2f\n" +
                                        "ССШ: %.2f\n" +
                                        "СКХ: %.2f\n" +
                                        "Тромбоциты: %.2f\n" +
                                        "СОТ: %.2f\n" +
                                        "Тромбокрит: %.2f\n" +
                                        "ИРТ: %.2f",
                                analysis.getDate(),
                                analysis.getLeik(),
                                analysis.getErit(),
                                analysis.getGemo(),
                                analysis.getGemat(),
                                analysis.getSOE(),
                                analysis.getSSH(),  // Добавлено
                                analysis.getSKH(),  // Добавлено
                                analysis.getTrombocit(),
                                analysis.getSOT(),   // Добавлено
                                analysis.getTrombokrit(),  // Добавлено
                                analysis.getIRT());  // Добавлено

                        analysisDataList.add(analysisText);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }.execute();
    }
}