package com.example.freelunch;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class ReedemLunchFragment extends BottomSheetDialogFragment {
private Button bottomRedeem;


    public ReedemLunchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View bottomRedeemLunchView = inflater.inflate(R.layout.fragment_reedem_lunch, container, false);



        return bottomRedeemLunchView;

    }
}