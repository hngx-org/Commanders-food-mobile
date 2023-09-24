package com.example.freelunch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PostSplashActivity extends AppCompatActivity {

    private Button create_account, signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_splash);

        create_account = findViewById(R.id.create_account);
        signUp = findViewById(R.id.sign_up);

        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createOrganization = new Intent(PostSplashActivity.this, CreateOrganizationActivity.class);
                startActivity(createOrganization);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(PostSplashActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });

    }
}