package com.mygeekbranch.tempapplication;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {
    SwitchCompat mSwitch;
    CheckBox mCheckBoxHim;
    CheckBox mCheckBoxSpeed;
    CheckBox mCheckBoxPrecip;
    TextView mTextViewHim;
    boolean isCheckHim;
    int mTemperature;
    Button mSetTemperature;
    Button mIncTemperature;
    Button mDecTemperature;
    TextView mChangeTempText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Setting");



        mSwitch = findViewById(R.id.switch2);
        mTextViewHim = findViewById(R.id.textViewHim);
        mCheckBoxHim = findViewById(R.id.checkBoxHimidity);

        if (savedInstanceState != null) {
            isCheckHim = savedInstanceState.getBoolean("Himidity Key");
            mCheckBoxHim.setChecked(isCheckHim);
        }

        mTextViewHim.setText(Boolean.toString(isCheckHim)) ;
        mSetTemperature = findViewById(R.id.setTemp);
        mTemperature = Singleton.getSingleton().temperature;
        mChangeTempText = findViewById(R.id.changeTemp);
        mChangeTempText.setText("Температура : " + mTemperature + " °");
        Log.d("SettingActivity", "onCreate: " + mCheckBoxHim.isChecked());

        mSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSwitch.isChecked())
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                else
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
            }
        });

        mCheckBoxHim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mCheckBoxHim.isChecked())
                    setTextViewHim();
                else
                    mTextViewHim.setText(Boolean.toString(false));
                isCheckHim = mCheckBoxHim.isChecked();

            }
        });
        mIncTemperature = findViewById(R.id.tempPlus);
        mDecTemperature = findViewById(R.id.tempMinus);

        mIncTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Singleton.getSingleton().increaseTemperature();
                mTemperature = Singleton.getSingleton().temperature;
                mChangeTempText.setText("Температура : " + mTemperature + " °");
            }
        });
        mDecTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Singleton.getSingleton().decreaseTemperature();
                mTemperature = Singleton.getSingleton().getTemperature();
                mChangeTempText.setText("Температура : " + mTemperature + " °");
            }
        });

    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("Himidity Key", mCheckBoxHim.isChecked());
        Log.d("SettingActivity", "onSaveInstanceState: " + isCheckHim);
    }

    //    @Override
//    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//       boolean is = savedInstanceState.getBoolean("Himidity Key");
//        mTextViewHim.setText(Boolean.toString(is));
//        mCheckBoxHim.setChecked(is);
//        Log.d("SettingActivity", "onRestoreInstanceState: "+ is);
//    }
    public void setTextViewHim() {
        mTextViewHim.setText(Boolean.toString(mCheckBoxHim.isChecked()));
    }

}
