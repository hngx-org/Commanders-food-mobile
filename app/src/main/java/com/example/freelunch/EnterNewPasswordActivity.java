package com.example.freelunch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.freelunch.utils.ApiService;
import com.example.freelunch.utils.ResetPasswordRequest;
import com.example.freelunch.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnterNewPasswordActivity extends AppCompatActivity {

    private EditText otp, newpassword;
    private Button confirm, returnhome;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_new_password);

        otp = findViewById(R.id.entermailotp);
        newpassword = findViewById(R.id.enternewpassword);

        confirm = findViewById(R.id.confamam);
        returnhome = findViewById(R.id.returnhomebtn);

        // Initialize ApiService using Retrofit
        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get OTP and new password from EditText fields
                String otpCode = otp.getText().toString();
                String newPassword = newpassword.getText().toString();

                // Create a ResetPasswordRequest object with the OTP and new password
                ResetPasswordRequest request = new ResetPasswordRequest(newPassword, otpCode);

                Call<Void> call = apiService.resetPassword(request);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(EnterNewPasswordActivity.this, "PASSWORD RESET SUCCESSFUL", Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(EnterNewPasswordActivity.this, "Failed to reset password.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        // Handle API call failure here
                        Toast.makeText(EnterNewPasswordActivity.this, "Failed to reset password.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        returnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(EnterNewPasswordActivity.this, LoginActivity.class);
                startActivity(home);
            }
        });
    }
}
