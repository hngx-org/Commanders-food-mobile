package com.example.freelunch.utils;

import com.google.gson.annotations.SerializedName;

public class LunchRequest {
    @SerializedName("receivers")
    private int[] receivers;

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("note")
    private String note;

    public LunchRequest(int[] receivers, int quantity, String note) {
        this.receivers = receivers;
        this.quantity = quantity;
        this.note = note;
    }

    public int[] getReceivers() {
        return receivers;
    }

    public void setReceivers(int[] receivers) {
        this.receivers = receivers;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
