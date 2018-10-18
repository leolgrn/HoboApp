package com.lesson.hobo.data.googleMapsAPI;

import android.util.Log;

import com.lesson.hobo.data.model.Hobo;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GoogleMapsAPIProvider {

    private static final String BASE_URL = "http://10.33.1.3:8080/";

    private GoogleMapsAPIService googleMapsAPIService;

    public GoogleMapsAPIProvider() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(createOkHttpClient(null))
                .build();
        googleMapsAPIService = retrofit.create(GoogleMapsAPIService.class);
    }

    public GoogleMapsAPIService getGoogleMapsAPIService() {
        return googleMapsAPIService;
    }

    private OkHttpClient createOkHttpClient(final String token) {
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        if(token != null){
            okBuilder.addInterceptor(new Interceptor() {
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request.Builder ongoing = chain.request().newBuilder();
                    ongoing.addHeader("Authorization", token);
                    return chain.proceed(ongoing.build());
                }});
        }
        return okBuilder.build();
    }

    // POST methods

    public void postHobo(Hobo hobo, final ApiListener<Object> listener) {
        googleMapsAPIService
                .postHobo(hobo)
                .enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        if(listener != null){
                            Log.d("RESPONSE CODE TAMERE", String.valueOf(response.code()));
                            if(response.code() == 200){
                                listener.onSuccess(response.body());
                            }  else {
                                listener.onSuccess(null);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        if (listener != null) listener.onError(t);
                    }
                });
    }
}
