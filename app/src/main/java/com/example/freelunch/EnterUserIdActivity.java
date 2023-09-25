package com.example.freelunch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.freelunch.utils.UserDataResponse;
import com.example.freelunch.utils.ApiService;
import com.example.freelunch.utils.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnterUserIdActivity extends AppCompatActivity {

    private ApiService apiService;
    private String accessToken; // Variable to store the access token

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_user_id);

        // Initialize your ApiService (you should have already configured it)
        apiService = ApiClient.getClient().create(ApiService.class);

        TextView userIdTextView = findViewById(R.id.txtUserid);
       // EditText editText = findViewById(R.id.edt_enterid);
        Button button = findViewById(R.id.btn_continuetoprofile);

        // Retrieve the user ID and access token from the intent extras (if available)
        int userId = getIntent().getIntExtra("id", 0);
        accessToken = getIntent().getStringExtra("access_token");
        userIdTextView.setText(String.valueOf(userId));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String inputText = editText.getText().toString();

               // if (!inputText.isEmpty()) {
                    // Make an API call to fetch user's data using the access token
                    Call<UserDataResponse> call = apiService.getUserData("Bearer " + accessToken);

                    call.enqueue(new Callback<UserDataResponse>() {
                        @Override
                        public void onResponse(Call<UserDataResponse> call, Response<UserDataResponse> response) {
                            if (response.isSuccessful()) {
                                UserDataResponse userDataResponse = response.body();
                                if (userDataResponse != null) {
                                    UserDataResponse.UserDetails userDetails = userDataResponse.getUserData().getUserDetails();

                                    // Access user details
                                    String name = userDetails.getName();
                                    String email = userDetails.getEmail();
                                    String profilePicUrl = userDetails.getProfilePic();
                                    Integer lunchCreditBalance = userDetails.getLunchCreditBalance();

                                    // Store the data in SharedPreferences
                                    SharedPreferences sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("name", name);
                                    editor.putString("email", email);
                                    editor.putString("profile_pic", profilePicUrl);
                                    if (lunchCreditBalance != null) {
                                        editor.putInt("lunch_credit_balance", lunchCreditBalance);
                                    }
                                    editor.apply();

                                    // Handle the user data as needed
                                    Intent nextPage = new Intent(EnterUserIdActivity.this, HomeActivity.class);
                                    nextPage.putExtra("name", name);
                                    nextPage.putExtra("email", email);
                                    nextPage.putExtra("profile_pic", profilePicUrl);
                                    if (lunchCreditBalance != null) {
                                        nextPage.putExtra("lunch_credit_balance", lunchCreditBalance);
                                    }
                                    nextPage.putExtra("access_token", accessToken); // Pass the access token
                                    startActivity(nextPage);
                                }
                            } else {
                                // Handle the case when the API call is not successful
                                Toast.makeText(EnterUserIdActivity.this, "Login failed.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<UserDataResponse> call, Throwable t) {
                            // Handle API call failure here
                            Toast.makeText(EnterUserIdActivity.this, "Login failed.", Toast.LENGTH_SHORT).show();
                        }
                    });
                //} else {
                    // Handle the case when the input is empty
                  //  Toast.makeText(EnterUserIdActivity.this, "Please enter a valid user ID", Toast.LENGTH_SHORT).show();
                //}
            }
        });
    }
}

