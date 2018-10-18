package com.lesson.hobo.data.googleMapsAPI;

import com.lesson.hobo.data.model.Hobo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GoogleMapsAPIService {
    // GET Methods

    @Headers("Content-Type: application/json")
    @POST("SDF/create")
    Call<Object> postHobo(@Body Hobo hobo);

}
