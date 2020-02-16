package com.example.eventscheduler.api;

import com.example.eventscheduler.model.Event;
import com.example.eventscheduler.model.User;
import com.example.eventscheduler.serverresponse.ImageResponse;
import com.example.eventscheduler.serverresponse.SignUpResponse;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface UsersAPI {

    @POST("users/signup")
    Call<SignUpResponse> registerUser(@Body User users);

    @FormUrlEncoded
    @POST("users/login")
    Call<SignUpResponse> checkUser(@Field("username") String username, @Field("password") String password);

    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

    @GET("users/me")
    Call<User> getUserDetails(@Header("Authorization")String token);

<<<<<<< HEAD
=======
    @PUT("/users/profile")
    Call<ResponseBody> updateProfile (@Header("Authorization") String token , @Body User user);

>>>>>>> origin/testing
    @GET("users/all")
    Call<List<User>> getUsers(@Header("Authorization")String token);



}
