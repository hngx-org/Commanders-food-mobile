package com.example.freelunch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateStaffAccountActivity extends AppCompatActivity {
    private EditText firstname, lastname, email, phonenumber, password;
    private Button nextbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_staff_account);

        firstname = findViewById(R.id.staffFname);
        lastname = findViewById(R.id.staffLname);
        email = findViewById(R.id.staffEmail);
        phonenumber = findViewById(R.id.staffPnumber);
        password = findViewById(R.id.staffPassword);

        nextbtn = findViewById(R.id.btn_next);
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }
}