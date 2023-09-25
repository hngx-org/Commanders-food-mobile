package com.example.freelunch.utils;

import java.io.Serializable;

public class UserDetails implements Serializable {
    private String name;
    private String email;
    private String profilePic;
    private Integer lunchCreditBalance;

    public UserDetails(String name, String email, String profilePic, Integer lunchCreditBalance) {
        this.name = name;
        this.email = email;
        this.profilePic = profilePic;
        this.lunchCreditBalance = lunchCreditBalance;
    }

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
