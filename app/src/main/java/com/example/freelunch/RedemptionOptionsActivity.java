package com.example.freelunch;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.freelunch.utils.ApiResponse;
import com.example.freelunch.utils.ApiService;
import com.example.freelunch.utils.LunchRequest; // Import LunchRequest

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RedemptionOptionsActivity extends AppCompatActivity {

    private EditText etReceivers, etQuantity, etNote;
    private Button btnRedeem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redemption_options);

        etReceivers = findViewById(R.id.etOption1);
        etQuantity = findViewById(R.id.etOption2);
        etNote = findViewById(R.id.etOption3);
        btnRedeem = findViewById(R.id.btnRedeem);

        btnRedeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int receivers = Integer.parseInt(etReceivers.getText().toString());
                int quantity = Integer.parseInt(etQuantity.getText().toString());
                String note = etNote.getText().toString();

                // Retrieve the access token from SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                String accessToken = sharedPreferences.getString("access_token", "");

                // Create a Retrofit instance
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://free-lunch-j9obk.ondigitalocean.app/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                // Create an instance of your ApiService interface
                ApiService apiService = retrofit.create(ApiService.class);

                // Create a LunchRequest object with the user input
                LunchRequest lunchRequest = new LunchRequest(
                        new int[]{receivers},
                        quantity,
                        note
                );

                // Make the API call with the retrieved access token
                Call<ApiResponse> call = apiService.sendLunchRequest("Bearer " + accessToken, lunchRequest);

                call.enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if (response.isSuccessful()) {
                            // Handle the successful API response here
                            ApiResponse apiResponse = response.body();
                            if (apiResponse != null) {
                                Toast.makeText(RedemptionOptionsActivity.this, "API call successful: " + apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RedemptionOptionsActivity.this, "API response is null", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Handle API error
                            Toast.makeText(RedemptionOptionsActivity.this, "API call failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        // Handle API call failure (e.g., network error)
                        Toast.makeText(RedemptionOptionsActivity.this, "API call failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
