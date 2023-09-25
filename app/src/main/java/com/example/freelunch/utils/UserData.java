package com.example.freelunch.utils;

import com.google.gson.annotations.SerializedName;

    public class UserData {
        @SerializedName("id")
        private int id;

        @SerializedName("name")
        private String name;


        @SerializedName("profile_pic")
        private String profilePic; // Assuming it's a URL to the profile picture

        @SerializedName("lunch_balance")
        private double lunchBalance;

        // Getter methods for the fields
        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getProfilePic() {
            return profilePic;
        }

        public double getLunchBalance() {
            return lunchBalance;
        }
    }

