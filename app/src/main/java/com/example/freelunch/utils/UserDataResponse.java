package com.example.freelunch.utils;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserDataResponse implements Serializable {
    @SerializedName("message")
    private String message;

    @SerializedName("statusCode")
    private int statusCode;

    @SerializedName("data")
    private UserData userData;

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public UserData getUserData() {
        return userData;
    }

    public static class UserData implements Serializable { // Make it Serializable
        @SerializedName("data")
        private UserDetails userDetails;

        public UserDetails getUserDetails() {
            return userDetails;
        }
    }

    public static class UserDetails implements Serializable { // Make it Serializable
        @SerializedName("name")
        private String name;

        @SerializedName("email")
        private String email;

        @SerializedName("profile_pic")
        private String profilePic;

        @SerializedName("lunch_credit_balance")
        private Integer lunchCreditBalance; // Integer to handle null value

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getProfilePic() {
            return profilePic;
        }

        public Integer getLunchCreditBalance() {
            return lunchCreditBalance;
        }
    }
}
