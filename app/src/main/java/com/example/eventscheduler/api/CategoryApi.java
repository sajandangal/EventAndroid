package com.example.eventscheduler.api;

import com.example.eventscheduler.model.Category;
import com.example.eventscheduler.model.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface CategoryApi {


    @GET("categories")
    Call<List<Category>> getCategory(@Header("Authorization")String token);

    snsjnfcsncnsjn

}
