package com.example.eventscheduler.api;

import com.example.eventscheduler.model.Notification;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface NotificationApi {

    @GET("notifications")
    Call<List<Notification>> getNotify(@Header("Authorization")String token);

//    @GET("events/id")
//    Call<List<Notification>> getEventbyid(@Header("Authorization")String token);


kncnckxnc
}
