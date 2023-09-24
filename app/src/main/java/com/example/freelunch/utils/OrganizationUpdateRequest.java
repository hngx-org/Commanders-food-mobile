package com.example.freelunch.utils;

import com.google.gson.annotations.SerializedName;

public class OrganizationUpdateRequest {
    @SerializedName("organization_name")
    private String organizationName;

    @SerializedName("lunch_price")
    private String lunchPrice;

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getLunchPrice() {
        return lunchPrice;
    }

    public void setLunchPrice(String lunchPrice) {
        this.lunchPrice = lunchPrice;
    }
}
