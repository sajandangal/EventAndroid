package com.example.eventscheduler;

import com.example.eventscheduler.model.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginApi {

    //for Register
    @POST("users/register")
    Call<Void> register(@Body UserModel user);


    //for Login
    @POST("users/login")
    Call<Void> login(@Body UserModel user);

}
