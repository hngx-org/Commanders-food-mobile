package com.example.freelunch;

import android.os.Bundle;
import android.content.Intent;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeFragment extends Fragment {
    private Button redeemButton;
    private ImageView imageView;
    private TextView username;
    private TextView lunchbal;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View homeFragmentView = inflater.inflate(R.layout.fragment_home, container, false);

        imageView = homeFragmentView.findViewById(R.id.profilePic);
        username = homeFragmentView.findViewById(R.id.txtName);
        lunchbal = homeFragmentView.findViewById(R.id.txtLunchCount);


        redeemButton = homeFragmentView.findViewById(R.id.btn_redeem_lunch);
        redeemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReedemLunchFragment reedemLunchFragment = new ReedemLunchFragment();
                reedemLunchFragment.show(getActivity().getSupportFragmentManager(), reedemLunchFragment.getTag());
            }
        });


        return homeFragmentView;
    }
}