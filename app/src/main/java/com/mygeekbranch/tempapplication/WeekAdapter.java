package com.mygeekbranch.tempapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WeekAdapter extends RecyclerView.Adapter<WeekAdapter.ViewHolder>{
    private  List<WeekWeatherModel> weekList;

    public WeekAdapter(List<WeekWeatherModel> weekList) {
        this.weekList = weekList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_week_weather, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.day.setText(weekList.get(position).getDate());
        holder.temp.setText(weekList.get(position).getTemperature());

    }

    @Override
    public int getItemCount() {
        return weekList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView day, temp;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.dayWeek);
            temp = itemView.findViewById(R.id.dayTemperature);
        }
    }
}
