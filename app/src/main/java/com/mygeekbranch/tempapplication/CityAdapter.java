package com.mygeekbranch.tempapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mygeekbranch.tempapplication.dataBase.App;
import com.mygeekbranch.tempapplication.modelWeather.GetWeatherRetrofit;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {
    private List< String> cityList ;
    SharedPreferences sharedPreferences;


    public CityAdapter(List<String> cityList) {
        this.cityList = cityList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       holder.city.setText(cityList.get(position));


    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView city;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            city = itemView.findViewById(R.id.textViewCity);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String city2 = (String) city.getText();
                    Toast.makeText(view.getContext(), city2, Toast.LENGTH_SHORT).show();
                    Singleton.getSingleton().setCurrentCity(city2);

                    GetWeatherRetrofit.initRetrofit();

                    sharedPreferences = App.getInstance().getCitypreferences();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("currentCity", city2);
                    editor.commit();

                    // Запуск интернест страницы википедии
//                    String url = "https://ru.wikipedia.org/wiki/"+ city2;
//                    Uri uri = Uri.parse(url);
//                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                    itemView.getContext().startActivity(intent);


                }
            });

        }
    }

}
