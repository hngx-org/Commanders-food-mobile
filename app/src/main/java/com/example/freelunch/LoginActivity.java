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
import com.example.freelunch.utils.LoginResponse;

import com.example.freelunch.utils.ApiService;
import com.example.freelunch.utils.LoginRequest;
import com.example.freelunch.utils.Tokens;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private Button loginBtn;
    private EditText username, password;
    public static ApiService apiService;
    private TextView forgetpassword, createacct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        forgetpassword = findViewById(R.id.txtForgotPassword);
        createacct = findViewById(R.id.txtCreateAcct);

        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgotPassword = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(forgotPassword);
            }
        });
        createacct = findViewById(R.id.txtCreateAcct);
        createacct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createorgacct = new Intent(LoginActivity.this, CreateStaffAccountActivity.class);
                startActivity(createorgacct);
            }
        });

        loginBtn = findViewById(R.id.loginButton);
        username = findViewById(R.id.edtUsername);
        password = findViewById(R.id.edtPassword);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://free-lunch-j9obk.ondigitalocean.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = username.getText().toString();
                String userPassword = password.getText().toString();

                LoginRequest loginRequest = new LoginRequest(userEmail, userPassword);



                Call<LoginResponse> call = apiService.login(loginRequest);

                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            LoginResponse loginResponse = response.body();
                            String accessToken = loginResponse.getData().getAccessToken();
                            int userId = loginResponse.getData().getId();

                            boolean isAdmin = loginResponse.getData().isAdmin();

                            if (isAdmin) {
                                // User is an admin, navigate to OrganizationDetailsActivity
                                Intent intent = new Intent(LoginActivity.this, OrganizationDetailsActivity.class);
                                intent.putExtra("access_token", loginResponse.getData().getAccessToken());

                                startActivity(intent);
                            }else {
                                // User is not an admin, navigate to another activity
                                Intent intenttwo = new Intent(LoginActivity.this, EnterUserIdActivity.class);
                                intenttwo.putExtra("id", loginResponse.getData().getId());
                                intenttwo.putExtra("access_token", loginResponse.getData().getAccessToken());
                                startActivity(intenttwo);
                            }

                        }else{
                            Toast.makeText(LoginActivity.this, "Login failed.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Login failed.", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });



    }

}

