package com.mygeekbranch.tempapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CityActivity extends AppCompatActivity implements Constants{
    TextView moscow;
    TextView cheboksary;
    TextView kazan;
    TextView nizhniy_novgorod;
    EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        mEditText = findViewById(R.id.edit_text);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("City");
        moscow = findViewById(R.id.textViewMsc);
        moscow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentResult = new Intent();
                intentResult.putExtra(RESULT, mEditText.getText().toString());
                setResult(RESULT_OK, intentResult);
                finish();
            }
        });

    }
}