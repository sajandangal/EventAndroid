package com.example.eventscheduler.api;

import com.example.eventscheduler.model.Category;
import com.example.eventscheduler.model.Event;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface EventAPI {

    @FormUrlEncoded
    @POST("events")
    Call<Void> addEvent(@Header("Authorization")String token, @Field("name") String event, @Field("desc") String event1, @Field("location") String event3, @Field("image") String event2,@Field("users") ArrayList<String> event4);
   // Call<Void> addEvent(@Header("Authorization")String token, @Field("name") String event, @Field("desc") String event1,@Field("image") String event2,@Field("location") String event3);

    @GET("events")
    Call<List<Event>> getEvent(@Header("Authorization")String token);

    @GET("categories")
    Call<List<Category>> getCategory(@Header("Authorization")String token);

//    @GET("notifications")
//    Call<List<Notification>> getNotification(@Header("Authorization")String token);




}