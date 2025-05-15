package com.example.diplom;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
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
                AnalysisAdapter adapter = new AnalysisAdapter(HistoryActivity.this, analyses);
                listView.setAdapter(adapter);
            }
        }.execute();
    }
    public void goBack(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
