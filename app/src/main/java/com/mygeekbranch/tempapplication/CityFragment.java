package com.mygeekbranch.tempapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CityFragment extends Fragment {

    Button setCity;

    public EditText mEditText;
    public List<String> cityList = Singleton.getSingleton().getCityList();


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
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("City");
        mEditText = view.findViewById(R.id.edit_text);

        setCity = view.findViewById(R.id.set_city);
        setCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cityList.add(mEditText.getText().toString());
                Singleton.getSingleton().setCurrentCity(mEditText.getText().toString());
                getActivity().finish();

            }
        });

        //initCityList();
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

    public void initCityList() {
        cityList = new ArrayList<>();
        cityList.add("Moscow");
        cityList.add("Moscow1");
        cityList.add("Moscow2");
        cityList.add("Moscow3");
        cityList.add("Moscow4");
        cityList.add("Moscow5");
        cityList.add("Moscow6");
        cityList.add("Moscow7");
        cityList.add("Moscow8");
    }




}