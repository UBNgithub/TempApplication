package com.mygeekbranch.tempapplication;

import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;


public class SettingFragment extends Fragment {
    private SwitchCompat mSwitch;
    private CheckBox mCheckBoxHim;
    private CheckBox mCheckBoxSpeed;
    private CheckBox mCheckBoxPrecip;
    private TextView mTextViewHim;
    boolean isCheckHum;
    boolean isCheckNightMode;
    private float mTemperature;
    private Button mSetTemperature;
    private Button mIncTemperature;
    private Button mDecTemperature;
    private TextView mChangeTempText;
    private Toolbar toolbar;
    private SharedPreferences sharedPreferences;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
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
        initPreferensis();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




        isCheckNightMode = Singleton.getSingleton().isCheckNightMode();
        mSwitch = view.findViewById(R.id.switch2);
        mSwitch.setChecked(isCheckNightMode);
        mSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSwitch.isChecked())
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                else
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

                Singleton.getSingleton().setCheckNightMode(mSwitch.isChecked());

            }
        });
        apdateAppBar();
        mTextViewHim = view.findViewById(R.id.textViewHim);
        setTextViewHim();
        mCheckBoxHim = view.findViewById(R.id.checkBoxHimidity);
        //mCheckBoxHim.setChecked(Singleton.getSingleton().isCheckHumidity());
        mCheckBoxHim.setChecked(sharedPreferences.getBoolean("isCheckHumidity", false));
        mCheckBoxHim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCheckBoxHim.isChecked())
                    setTextViewHim();
                else
                    mTextViewHim.setText(Boolean.toString(false));
                Singleton.getSingleton().setCheckHumidity(mCheckBoxHim.isChecked());
                savePreferences(mCheckBoxHim.isChecked());
                setTextViewHim();

            }
        });
        mTemperature = Singleton.getSingleton().getTemperatureFloat();
        mChangeTempText = view.findViewById(R.id.changeTemp);
        mChangeTempText.setText("Температура : " + mTemperature + " °");

        mIncTemperature = view.findViewById(R.id.tempPlus);
        mDecTemperature = view.findViewById(R.id.tempMinus);

        mIncTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Singleton.getSingleton().increaseTemperature();
                mTemperature = Singleton.getSingleton().getTemperatureFloat();
                mChangeTempText.setText("Температура : " + mTemperature + " °");

            }
        });
        mDecTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Singleton.getSingleton().decreaseTemperature();
                mTemperature = Singleton.getSingleton().getTemperatureFloat();
                mChangeTempText.setText("Температура : " + mTemperature + " °");
            }
        });

        //((AppCompatActivity) getActivity()).getSupportActionBar(toolbar);
        // toolbar = (Toolbar) getActivity().findViewById(R.id.toolbarMain);
        //toolbar.setTitle("Setting");
        // toolbar.setNavigationIcon(null);


    }




    public void setTextViewHim() {
        mTextViewHim.setText("Влажность: " + Boolean.toString(Singleton.getSingleton().isCheckHumidity()));
    }

    private void apdateAppBar() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        String currentCity = "Setting";
        activity.getSupportActionBar().setTitle(currentCity);
    }
    private void initPreferensis() {
        sharedPreferences = MainActivity.getInstance().getPreferences(Context.MODE_PRIVATE);
        //loadPreferences();
    }
     //Загрузка настроек
    private void loadPreferences() {
        Boolean loadIsCheckHumidity = sharedPreferences.getBoolean("isCheckHumidity", false);
    }
    //Сохранение настроек
    private void savePreferences(Boolean isCheckHum){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isCheckHumidity",isCheckHum);
        editor.commit();

    }



}