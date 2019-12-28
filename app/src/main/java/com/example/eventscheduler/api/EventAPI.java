package com.example.eventscheduler.api;

import com.example.eventscheduler.model.Event;

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
    Call<Void> addEvent(@Header("Authorization")String token, @Field("name") String event);

    @GET("events")
    Call<List<Event>> getEvent(@Header("Authorization")String token);

}