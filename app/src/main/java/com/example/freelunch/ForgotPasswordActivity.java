package com.example.freelunch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.freelunch.utils.ForgotPasswordRequest;
import com.example.freelunch.utils.ApiService;
import com.example.freelunch.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {
    private EditText mail;
    private Button btn;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        mail = findViewById(R.id.edtEnteremail);
        btn = findViewById(R.id.sendpasswordotp);

        // Initialize ApiService using Retrofit
        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the email entered by the user
                String email = mail.getText().toString();

                // Create a ForgotPasswordRequest object with the email
                ForgotPasswordRequest request = new ForgotPasswordRequest(email);

                // Make the API call to request a password reset
                Call<Void> call = apiService.forgotPassword(request);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            // startActivity(new Intent(ForgotPasswordActivity.this, ConfirmationActivity.class));
                            Intent confirm = new Intent(ForgotPasswordActivity.this, EnterNewPasswordActivity.class);
                            startActivity(confirm);
                        } else {

                            Toast.makeText(ForgotPasswordActivity.this, "Failed to request password reset.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        // Handle API call failure here
                        Toast.makeText(ForgotPasswordActivity.this, "Failed to request password reset.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
