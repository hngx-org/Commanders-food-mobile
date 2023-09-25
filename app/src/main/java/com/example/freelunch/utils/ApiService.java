package com.example.freelunch.utils;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiService {
    @POST("api/auth/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    // Define the organization update API endpoint
    @PUT("api/organization/create")
    Call<ApiResponse> updateOrganizationInfo(@Header("Authorization") String authorization, @Body OrganizationUpdateRequest organizationUpdateRequest);

    // New endpoint for sending invites
    @POST("api/organization/invite")
    Call<Void> sendInvite(
            @Header("Authorization") String authorization,
            @Body InviteRequest inviteRequest
    );
    @POST("api/lunch/send")
    Call<ApiResponse> sendLunchRequest(
            @Header("Authorization") String authorization,
            @Body CreateLunchSendRequest request
    );

    // Define a GET request to fetch user data by userId
    @GET("api/user/profile")
    Call<UserDataResponse> getUserData(
            @Header("Authorization") String authorizationHeader
    );

    @POST("api/user/forgot-password")
    Call<Void> forgotPassword(@Body ForgotPasswordRequest request);

    @POST("api/user/reset-password")
    Call<Void> resetPassword(@Body ResetPasswordRequest request);

    @POST("api/lunch/send")
    Call<ApiResponse> sendLunchRequest(
            @Header("Authorization") String authorization,
            @Body LunchRequest lunchRequest
    );


}
