package com.example.freelunch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.freelunch.utils.ApiResponse;
import com.example.freelunch.utils.ApiService;
import com.example.freelunch.utils.CreateLunchSendRequest;
import com.example.freelunch.utils.InviteRequest;
import com.example.freelunch.utils.OrganizationUpdateRequest;
import com.example.freelunch.utils.SendLunchRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrganizationDetailsActivity extends AppCompatActivity {
    private EditText organizationNameInput, lunchPriceInput, inviteEmail, sendId, sendQuantity, sendNote;
    private Button finishButton, invitebutton, sendLunch;

    private ApiService organizationApiService;
    private String storedAccessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_details);

        organizationNameInput = findViewById(R.id.edt_input_orgName);
        lunchPriceInput = findViewById(R.id.edt_input_lunch_price);
        inviteEmail = findViewById(R.id.edt_email_invite);
        finishButton = findViewById(R.id.btn_finish);
        invitebutton = findViewById(R.id.btn_send_invite);
        sendId = findViewById(R.id.edt_enter_user_id);
        sendQuantity = findViewById(R.id.edt_enter_quantity);
        sendNote = findViewById(R.id.edt_notes);
        sendLunch = findViewById(R.id.btn_admin_sendLunch);


        // Retrieve the access token from the Intent
        storedAccessToken = getIntent().getStringExtra("access_token");

        // Initialize Retrofit and the API service
        initializeRetrofit();




        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if access token is available
                if (storedAccessToken != null) {
                    // Create the request body
                    OrganizationUpdateRequest requestBody = createRequestBody();

                    // Make the PUT request
                    makeOrganizationUpdateRequest(requestBody);
                } else {
                    Toast.makeText(OrganizationDetailsActivity.this, "Access token not found.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        invitebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if access token is available
                if (storedAccessToken != null) {
                    // Create the invite request body
                    String emailInvite = inviteEmail.getText().toString();
                    InviteRequest inviteRequest = new InviteRequest(emailInvite);

                    // Make the POST request to send an invite
                    makeInviteRequest(inviteRequest);
                } else {
                    Toast.makeText(OrganizationDetailsActivity.this, "Access token not found.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        sendLunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (storedAccessToken != null) {
                    int[] receivers = new int[]{Integer.parseInt(sendId.getText().toString())};
                    int quantity = Integer.parseInt(sendQuantity.getText().toString());
                    String note = sendNote.getText().toString();

                    // Create the request body
                    CreateLunchSendRequest createLunchSendRequest = new CreateLunchSendRequest(receivers,quantity,note);

                    // Make the POST request to send lunch
                    makeSendLunchRequest(createLunchSendRequest);
                } else {
                    Toast.makeText(OrganizationDetailsActivity.this, "Access token not found.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void initializeRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://free-lunch-j9obk.ondigitalocean.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        organizationApiService = retrofit.create(ApiService.class);
    }

    private OrganizationUpdateRequest createRequestBody() {
        // Create an instance of your request body class
        OrganizationUpdateRequest requestBody = new OrganizationUpdateRequest();

        // Set the values of your request body based on user input or other data
        requestBody.setOrganizationName(organizationNameInput.getText().toString());
        requestBody.setLunchPrice(lunchPriceInput.getText().toString());

        return requestBody;
    }

    private void makeOrganizationUpdateRequest(OrganizationUpdateRequest requestBody) {
        // Create a Retrofit call with the stored access token as an authorization header
        String authorizationHeader = "Bearer " + storedAccessToken;
        Call<ApiResponse> call = organizationApiService.updateOrganizationInfo(authorizationHeader, requestBody);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    // Handle a successful response (e.g., organization info updated)
                    String message = response.body().getMessage();
                    Toast.makeText(OrganizationDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
                } else {
                    // Handle an unsuccessful response (e.g., failed to update organization info)
                    Toast.makeText(OrganizationDetailsActivity.this, "Failed to update organization info", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                // Handle a network error or other failure
                Toast.makeText(OrganizationDetailsActivity.this, "Failed to update organization info", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void makeInviteRequest(InviteRequest inviteRequest) {
        // Make the POST request with the stored access token
        Call<Void> call = organizationApiService.sendInvite("Bearer " + storedAccessToken, inviteRequest);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Handle a successful response (e.g., invite sent)
                    Toast.makeText(OrganizationDetailsActivity.this, "Invite sent successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle an unsuccessful response (e.g., failed to send invite)
                    Toast.makeText(OrganizationDetailsActivity.this, "Failed to send invite", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Handle a network error or other failure
                Toast.makeText(OrganizationDetailsActivity.this, "Failed to send invite", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void makeSendLunchRequest(CreateLunchSendRequest createLunchSendRequest) {
        // Make the POST request with the stored access token
        Call<ApiResponse> call = organizationApiService.sendLunchRequest("Bearer " + storedAccessToken, createLunchSendRequest);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    // Handle a successful response (e.g., lunch sent)
                    Toast.makeText(OrganizationDetailsActivity.this, "Lunch sent successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle an unsuccessful response (e.g., failed to send lunch)
                    Toast.makeText(OrganizationDetailsActivity.this, "Failed to send lunch", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                // Handle a network error or other failure
                Toast.makeText(OrganizationDetailsActivity.this, "Failed to send lunch", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
