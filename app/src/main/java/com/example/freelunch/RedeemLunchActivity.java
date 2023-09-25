package com.example.freelunch;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class RedeemLunchActivity extends AppCompatActivity {

    private EditText redeemTokenEditText;
    private MaterialButton redeemButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeem_lunch);

        redeemTokenEditText = findViewById(R.id.redeemtoken);
        redeemButton = findViewById(R.id.redeemlunc);

        redeemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered token from the EditText
                String redeemToken = redeemTokenEditText.getText().toString();

                // Display a Toast message to indicate the redemption status
                Toast.makeText(RedeemLunchActivity.this, "Redemption Token: " + redeemToken, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
