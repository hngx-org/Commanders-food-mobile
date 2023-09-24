package com.example.freelunch;

import android.content.Intent;
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

        bottomRedeem = bottomRedeemLunchView.findViewById(R.id.btn_withdraw);
        bottomRedeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
                // Create and show the second dialog
                RedeemLunchSucessFragment redeemLunchSucessFragment = new RedeemLunchSucessFragment();
                redeemLunchSucessFragment.show(requireActivity().getSupportFragmentManager(), RedeemLunchSucessFragment.class.getSimpleName());
            }
        });

        return bottomRedeemLunchView;

    }
}