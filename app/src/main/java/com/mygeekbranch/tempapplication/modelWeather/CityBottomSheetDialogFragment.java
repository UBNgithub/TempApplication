package com.mygeekbranch.tempapplication.modelWeather;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.mygeekbranch.tempapplication.CityDialogFragment;
import com.mygeekbranch.tempapplication.R;

public class CityBottomSheetDialogFragment extends BottomSheetDialogFragment {

    public static CityBottomSheetDialogFragment newInstance(){
        return new CityBottomSheetDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_dialog_city,container,false);

        return view;
    }
}
