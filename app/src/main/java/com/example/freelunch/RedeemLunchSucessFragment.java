package com.example.freelunch;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class RedeemLunchSucessFragment extends BottomSheetDialogFragment {
    private Button sucessfulbtn;

    public RedeemLunchSucessFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View RedeemSuccesful = inflater.inflate(R.layout.redeem_lunch_sucess, container, false);

        sucessfulbtn = RedeemSuccesful.findViewById(R.id.btn_successful);
        sucessfulbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backHome = new Intent(getActivity(), HomeFragment.class);
                startActivity(backHome);
            }
        });



        return RedeemSuccesful;

    }
}
