package com.mygeekbranch.tempapplication;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.impl.WorkManagerImpl;


import com.mygeekbranch.tempapplication.modelWeather.GetUrlData;

import com.mygeekbranch.tempapplication.modelWeather.GetWeatherRetrofit;
import com.mygeekbranch.tempapplication.modelWeather.GetWeatherService;
import com.mygeekbranch.tempapplication.modelWeather.GetWeatherWorker;
import com.mygeekbranch.tempapplication.modelWeather.WeatherInit;
import com.mygeekbranch.tempapplication.modelWeather.model.Weather;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class MainFragment extends Fragment {
    TextView dateText;
    TextView mTemperature;
    TextView mPasmurno;
    Toolbar toolbar;

    TemperatureView temperatureView;
    List<WeekWeatherModel> weekList;
    public float temp;
    public Button setTempCastView;
    private ImageView imageViewMain;
    SharedPreferences sharedPreferences;

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
        initPreferenses();


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


        mTemperature = view.findViewById(R.id.temperatureTVMain);
        mPasmurno = view.findViewById(R.id.weatherTVMain);
        temperatureView = view.findViewById(R.id.button);

        temp = Singleton.getSingleton().getTemperatureFloat();
        mTemperature.setText(temp + " °");
        mPasmurno.setText("Пасмурно");

        temperatureView.setTemperatureLevel(temp);


        setTempCastView = view.findViewById(R.id.SetButton);
        setTempCastView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"Set temp click", Toast.LENGTH_SHORT).show();
               // MainActivity.getInstance().startService(new Intent(getActivity(), GetWeatherService.class));
                makeNote("Нотификация");
                //MainActivity.getInstance().WeatherInit();
                // GetUrlData.Init();
               // GetWeatherRetrofit.initRetrofit();
                Toast.makeText(getActivity(),Singleton.getSingleton().getCurrentCity(), Toast.LENGTH_SHORT).show();
                float temp = Singleton.getSingleton().getTemperatureFloat();
                String tempString = String.valueOf(temp);
                Toast.makeText(getActivity(),tempString, Toast.LENGTH_SHORT).show();
                GetWeatherRetrofit.initRetrofit();
                temp = Singleton.getSingleton().getTemperatureFloat();
                mTemperature.setText(temp + " °");




            }
        });
        imageViewMain = (ImageView) view.findViewById(R.id.imageViewMain);
        setImagePicasso();

// использую поток чтобы обновить главный фрагмент
        // т.к. запрос API происходит в отдельном потоке а View уже инициализировалась
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (Singleton.getSingleton().getMainFragmentCount() == 0) {

                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container_main, MainFragment.newInstance(null, null))
                            .commit();

                    Log.d("Поток", "run: ");
                    Singleton.getSingleton().setMainFragmentCount(1);
                }

            }
        };
        handler.postDelayed(runnable, 900);
        // handler.post(runnable);


        initListWeekWeather();
        initRecyclerView(view);
        apdateAppBar();
    }

    private void setImagePicasso() {
       // String iconImageWR = "10d";
        String iconImageWR = Singleton.getSingleton().getIcon();


        String path = "http://openweathermap.org/img/wn/" +iconImageWR + "@2x.png";
        Picasso.get()
                .load(path)
                .error(R.drawable.icon)
                .into(imageViewMain);
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

    private void apdateAppBar() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        String currentCity = Singleton.getSingleton().getCurrentCity();
        //activity.getSupportActionBar().setTitle(currentCity);
        // Boolean loadIsCheckHumidity = sharedPreferences.getBoolean("isCheckHumidity", false);
        activity.getSupportActionBar().setTitle(sharedPreferences.getString("currentCity", "City"));


    }

    // Метод с нотификацией:
    private void makeNote(String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), "2")
                .setSmallIcon(R.drawable.icon)
                .setContentTitle("Main Fragment notification")
                .setContentText(message);
        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }
    //инициализация Preferences
    private void initPreferenses() {
        sharedPreferences = MainActivity.getInstance().getSharedPreferences("CitySP",0);
        //loadPreferences();
    }


}