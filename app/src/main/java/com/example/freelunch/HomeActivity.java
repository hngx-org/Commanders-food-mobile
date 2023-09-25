package com.example.freelunch;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.freelunch.utils.UserDataResponse;
import com.example.freelunch.utils.UserDetails;
import com.squareup.picasso.Picasso;
import com.example.freelunch.EnterUserIdActivity;

public class HomeActivity extends AppCompatActivity {

    private ImageView imgProfilePic;
    private TextView txtName;
    private TextView txtEmail;
    private TextView txtLunchCreditBalance;
    private Button redeem;
    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); // Set the correct layout XML

        redeem = findViewById(R.id.btn_redeem_lunch);
        redeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create and show the RedemptionOptionsFragment
               Intent redeem = new Intent(HomeActivity.this, RedemptionOptionsActivity.class);
               startActivity(redeem);
            }
        });

        // Retrieve data from Intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        String profilePicUrl = intent.getStringExtra("profile_pic");
        int lunchCreditBalance = intent.getIntExtra("lunch_credit_balance", 0);

        // Retrieve data from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);
        name = sharedPreferences.getString("name", name);
        email = sharedPreferences.getString("email", email);
        profilePicUrl = sharedPreferences.getString("profile_pic", profilePicUrl);
        lunchCreditBalance = sharedPreferences.getInt("lunch_credit_balance", lunchCreditBalance);

        // Initialize views
        imgProfilePic = findViewById(R.id.profilePic);
        txtName = findViewById(R.id.txtuser);
        txtEmail = findViewById(R.id.txtId);
        txtLunchCreditBalance = findViewById(R.id.txtLunchCount);


        txtName.setText(name);
        txtEmail.setText(email);
        txtLunchCreditBalance.setText(""+lunchCreditBalance);

        // Load profile picture using Picasso
        //Picasso.get().load(profilePicUrl).into(imgProfilePic);



    }
}
