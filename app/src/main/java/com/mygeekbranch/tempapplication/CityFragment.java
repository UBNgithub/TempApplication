package com.mygeekbranch.tempapplication;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.mygeekbranch.tempapplication.dataBase.App;
import com.mygeekbranch.tempapplication.dataBase.City;
import com.mygeekbranch.tempapplication.dataBase.CityDao;
import com.mygeekbranch.tempapplication.dataBase.CityDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CityFragment extends Fragment {

    Button setCity;
    Toolbar toolbar;
    public TextInputEditText mEditText;
    public List<String> cityList = Singleton.getSingleton().getCityList();

    SharedPreferences sharedPreferences;
    // Инициализация БД
    CityDatabase  db = App.getInstance().getDb();
    CityDao cityDao = db.getCityDao();













    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CityFragment() {

    }


    // TODO: Rename and change types and number of parameters
    public static CityFragment newInstance(String param1, String param2) {
        CityFragment fragment = new CityFragment();
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
        initPreferenses();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_city_2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("City");
        mEditText = view.findViewById(R.id.text_input_edittext);
        apdateAppBar();

        setCity = view.findViewById(R.id.set_city);
        setCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // cityList.add(mEditText.getText().toString());
                // Singleton.getSingleton().setCurrentCity(mEditText.getText().toString());

                Snackbar.make(view, "Подтвердите выбор", Snackbar.LENGTH_LONG).setAction("Ok",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(getContext(), "Город выбран", Toast.LENGTH_SHORT).show();
                                cityList.add(mEditText.getText().toString());
                                Singleton.getSingleton().setCurrentCity(mEditText.getText().toString());
                                savePreferences(mEditText.getText().toString());
                                //getActivity().finish();


//                                CityDatabase db = App.getInstance().getDb();
//                                CityDao cityDao = db.getCityDao();
                                City city= new City();
                                city.city = mEditText.getText().toString();
                                cityDao.insertCity(city);

                            }
                        }).show();


            }
        });

        initRecyclerViewCity(view);


    }

    public void initRecyclerViewCity(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.city_recyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        CityAdapter adapter = new CityAdapter(cityList);
        recyclerView.setAdapter(adapter);
    }

    private void apdateAppBar() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        String city = "City";
        activity.getSupportActionBar().setTitle(city);
    }
    //инициализация Preferences
    private void initPreferenses() {
        //sharedPreferences = MainActivity.getInstance().getSharedPreferences("CitySP",0);
        //loadPreferences();
        sharedPreferences = App.getInstance().getCitypreferences();
    }
    //Сохранение текущего города в SharedPreferences
    private void savePreferences(String currentCity){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("currentCity",currentCity);
        editor.commit();

    }



}