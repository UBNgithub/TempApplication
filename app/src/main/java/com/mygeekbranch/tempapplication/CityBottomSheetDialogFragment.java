package com.mygeekbranch.tempapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;



public  class CityBottomSheetDialogFragment extends BottomSheetDialogFragment {
    private TextInputEditText textInputEditText ;
    private Button buttonCancel;
    
    private  OnDialogListener dialogListener;

    public static CityBottomSheetDialogFragment newInstance(){
        return new CityBottomSheetDialogFragment();
    }

    public void setDialogListener(OnDialogListener dialogListener) {
        this.dialogListener = dialogListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_dialog_city,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textInputEditText = view.findViewById(R.id.city_edit_text);
        
        view.findViewById(R.id.button_set).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                Toast.makeText(MainActivity.getInstance(),"Clic set", Toast.LENGTH_LONG).show();
                Singleton.getSingleton().setCurrentCity(textInputEditText.getText().toString());
            }
            
        });
        buttonCancel =view.findViewById(R.id.button_cancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
                Toast.makeText(MainActivity.getInstance(),"CLIC cancel", Toast.LENGTH_LONG).show();

            }
        });
        
       

    }
}