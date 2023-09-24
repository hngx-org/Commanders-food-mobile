package com.example.freelunch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freelunch.utils.SignupRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CreateOrganizationActivity extends AppCompatActivity {
    private Button createAccount;
    private EditText fname, lname, email, password, phonenumber;
    private TextView signInTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_organization);

        signInTextview = findViewById(R.id.txt_sign_in);
        createAccount = findViewById(R.id.btn_createOrgAcct);
        fname = findViewById(R.id.orgFName);
        lname = findViewById(R.id.orgLName);
        email = findViewById(R.id.orgEmail);
        password = findViewById(R.id.orgPassword);
        phonenumber = findViewById(R.id.orgPNumber);

        signInTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextPageIntent = new Intent(CreateOrganizationActivity.this, LoginActivity.class);
                startActivity(nextPageIntent);
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input
                String firstName = fname.getText().toString();
                String lastName = lname.getText().toString();
                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();
                String userPhoneNumber = phonenumber.getText().toString();

                // Create a SignupRequest object
                SignupRequest signupRequest = new SignupRequest(
                        userEmail,
                        userPassword,
                        firstName,
                        lastName,
                        userPhoneNumber
                );

                // Make the API call asynchronously
                new SignupTask().execute(signupRequest);
            }
        });
    }

    // AsyncTask to make the API call
    private class SignupTask extends AsyncTask<SignupRequest, Void, Boolean> {
        @Override
        protected Boolean doInBackground(SignupRequest... params) {
            String apiUrl = "https://free-lunch-j9obk.ondigitalocean.app/api/auth/user/signup";

            try {
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // Set up the request
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                // Create the request body JSON
                JSONObject requestBody = new JSONObject();
                requestBody.put("email", params[0].getEmail());
                requestBody.put("password", params[0].getPassword());
                requestBody.put("first_name", params[0].getFirst_name());
                requestBody.put("last_name", params[0].getLast_name());
                requestBody.put("phone_number", params[0].getPhone_number());

                // Write the request body to the output stream
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = requestBody.toString().getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                // Get the response
                int responseCode = connection.getResponseCode();
                return responseCode == HttpURLConnection.HTTP_OK;

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean isSuccessful) {
            if (isSuccessful) {
                // API call was successful, navigate to the next activity
                Intent intent = new Intent(CreateOrganizationActivity.this, LoginActivity.class);
                startActivity(intent);
            } else {
                // Handle error, show a message, or retry the API call
                Toast.makeText(CreateOrganizationActivity.this, "API call failed.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
