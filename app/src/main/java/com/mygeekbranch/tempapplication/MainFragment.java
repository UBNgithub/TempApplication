package com.mygeekbranch.tempapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mygeekbranch.tempapplication.modelWeather.WeatherInit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainFragment extends Fragment {
    TextView dateText;
    TextView mTemperature;
    TextView mPasmurno;
    Toolbar toolbar;
    TemperatureView temperatureView;
    List<WeekWeatherModel> weekList;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainFragment() {
    }


    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        //WeatherInit.Init(getActivity());
        //Toast.makeText(getActivity(), Singleton.getSingleton().getTemperature(), Toast.LENGTH_LONG).show();
        Log.d("TEMPERATURE MaimFragment =", Singleton.getSingleton().getTemperature());



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_main_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        dateText = view.findViewById(R.id.textViewDateMain);
        Date date = new Date();
        dateText.setText(date.toString());

        //String temp = Singleton.getSingleton().temperature;
        float temp = Singleton.getSingleton().getTemperatureFloat();
        mTemperature = view.findViewById(R.id.temperatureTVMain);

        mTemperature.setText(temp + " °");

        mPasmurno = view.findViewById(R.id.weatherTVMain);
        mPasmurno.setText("Пасмурно");

        temperatureView = view.findViewById(R.id.button);
        temperatureView.setTemperatureLevel(temp);

       // toolbar = (Toolbar) getActivity().findViewById(R.id.toolbarMain);
        //toolbar.setTitle(Singleton.getSingleton().getCurrentCity());
       // toolbar.inflateMenu(R.menu.main);
       // toolbar.setNavigationIcon(null);




        // toolbar.setTitle("CurrentCity");

        initListWeekWeather();
        initRecyclerView(view);
        apdateAppBar();
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.week_recyclerView2);
        recyclerView.setHasFixedSize(true);

        //Декоратор
        DividerItemDecoration decoration = new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        decoration.setDrawable(getActivity().getDrawable(R.drawable.item_separator));
        recyclerView.addItemDecoration(decoration);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        WeekAdapter adapter = new WeekAdapter(weekList);
        recyclerView.setAdapter(adapter);


    }

    private void initListWeekWeather() {
        weekList = new ArrayList<>();
        weekList.add(new WeekWeatherModel("ПН", "+9°"));
        weekList.add(new WeekWeatherModel("ВТ", "+5°"));
        weekList.add(new WeekWeatherModel("СР", "+4°"));
        weekList.add(new WeekWeatherModel("ЧТ", "+10°"));
        weekList.add(new WeekWeatherModel("ПT", "+12°"));
        weekList.add(new WeekWeatherModel("СБ", "+18°"));
        weekList.add(new WeekWeatherModel("ВС", "+20°"));
    }
    private  void apdateAppBar (){
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        String currentCity= Singleton.getSingleton().getCurrentCity();
        activity.getSupportActionBar().setTitle(currentCity);
    }

}