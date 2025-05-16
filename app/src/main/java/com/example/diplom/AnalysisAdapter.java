package com.example.diplom;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.Locale;

public class AnalysisAdapter extends ArrayAdapter<Information> {
    private Context context;
    private List<Information> analyses;
    private InformationDAO dao;

    public AnalysisAdapter(Context context, List<Information> analyses, InformationDAO dao) {
        super(context, R.layout.list_item, analyses);
        this.context = context;
        this.analyses = analyses;
        this.dao = dao;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.tvDate = convertView.findViewById(R.id.tvDate);
            holder.tvParams = convertView.findViewById(R.id.tvParams);
            holder.tvValues = convertView.findViewById(R.id.tvValues);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Information analysis = analyses.get(position);

        // Установка данных
        holder.tvDate.setText("Дата: " + analysis.getDate());

        String params = "Лейкоциты\nЭритроциты\nГемоглобин\nГематокрит\nСОЭ";
        String values = String.format(Locale.getDefault(),
                "%.2f\n%.2f\n%.2f\n%.2f\n%.2f",
                analysis.getLeik(),
                analysis.getErit(),
                analysis.getGemo(),
                analysis.getGemat(),
                analysis.getSOE());

        holder.tvParams.setText(params);
        holder.tvValues.setText(values);

        // Изменение фона для четных/нечетных позиций (пример)
        if (position % 2 == 0) {
            convertView.setBackgroundResource(R.drawable.item_background_alternate);
        } else {
            convertView.setBackgroundResource(R.drawable.item_background);
        }

        return convertView;
    }

    public void removeItem(int position) {
        Information item = analyses.get(position);
        new Thread(() -> {
            dao.delete(item);
            ((Activity)context).runOnUiThread(() -> {
                analyses.remove(position);
                notifyDataSetChanged();
            });
        }).start();
    }

    static class ViewHolder {
        TextView tvDate;
        TextView tvParams;
        TextView tvValues;
    }
}