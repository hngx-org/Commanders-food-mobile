package com.example.freelunch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseAccoutActivity extends AppCompatActivity {
    private Button createOrg, createUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_account);

        createOrg = findViewById(R.id.btnCreateOrg);
        createOrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createOrgIntent = new Intent(ChooseAccoutActivity.this, CreateOrganizationActivity.class);
                startActivity(createOrgIntent);
            }
        });
        createUser = findViewById(R.id.btnCreateUser);
        createUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createUserIntent = new Intent(ChooseAccoutActivity.this, CreateStaffAccountActivity.class);
                startActivity(createUserIntent);
            }
        });
    }
}