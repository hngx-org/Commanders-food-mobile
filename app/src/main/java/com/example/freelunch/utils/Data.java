package com.example.freelunch.utils;

import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("refresh_token")
    private String refreshToken;

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("is_admin")
    private boolean isAdmin;

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

}