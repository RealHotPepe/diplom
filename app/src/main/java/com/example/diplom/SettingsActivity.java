package com.example.diplom;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    private AppDatabase db;
    private UserSettingsDao userSettingsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        db = AppDatabase.getInstance(this);
        userSettingsDao = db.userSettingsDao();

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        initializeSettings(radioGroup);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            String gender = checkedId == R.id.male ? "male" : "female";
            updateGender(gender);
        });
    }

    private void initializeSettings(RadioGroup radioGroup) {
        AppExecutors.getInstance().diskIO().execute(() -> {
            try {
                // Проверяем существование записи
                if (userSettingsDao.getCurrentSettings() == null) {
                    UserSettings defaultSettings = new UserSettings();
                    userSettingsDao.insertSettings(defaultSettings);
                }

                // Загружаем текущие настройки
                UserSettings settings = userSettingsDao.getCurrentSettings();
                runOnUiThread(() -> {
                    if ("male".equals(settings.gender)) {
                        radioGroup.check(R.id.male);
                    } else {
                        radioGroup.check(R.id.female);
                    }
                });
            } catch (Exception e) {
                Log.e("SettingsActivity", "Error initializing settings", e);
            }
        });
    }

    private void updateGender(String gender) {
        AppExecutors.getInstance().diskIO().execute(() -> {
            try {
                userSettingsDao.updateGender(gender);
            } catch (Exception e) {
                Log.e("SettingsActivity", "Error updating gender", e);
            }
        });
    }

    public void goBack(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}