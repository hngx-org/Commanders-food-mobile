package com.example.freelunch.utils;


import com.google.gson.annotations.SerializedName;

public class ResetPasswordRequest {
    @SerializedName("new_password")
    private String newPassword;

    @SerializedName("otp_code")
    private String otpCode;

    public ResetPasswordRequest(String newPassword, String otpCode) {
        this.newPassword = newPassword;
        this.otpCode = otpCode;
    }
}
