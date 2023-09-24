package com.example.freelunch.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.freelunch.R;

import org.w3c.dom.Text;

public class OTPVerificationDialog extends Dialog {
    private EditText otp_1, otp_2, otp_3, otp_4, otp_5, otp_6;
    private Button cpntinuebtn;
    private TextView resendbtn;

    public OTPVerificationDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_dialog_box);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(getContext().getResources().getColor(android.R.color.transparent)));

        otp_1 = findViewById(R.id.otp1);
        otp_2 = findViewById(R.id.otp2);
        otp_3 = findViewById(R.id.otp3);
        otp_4 = findViewById(R.id.otp4);
        otp_5 = findViewById(R.id.otp5);
        otp_6 = findViewById(R.id.otp6);

        cpntinuebtn = findViewById(R.id.otp_received);
        resendbtn = findViewById(R.id.resendotp);

       /* otp_1.addTextChangedListener(textWatcher);
        otp_2.addTextChangedListener(textWatcher);
        otp_3.addTextChangedListener(textWatcher);
        otp_4.addTextChangedListener(textWatcher);
        otp_5.addTextChangedListener(textWatcher);
        otp_6.addTextChangedListener(textWatcher);*/


    }

    /*private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }*/
}
