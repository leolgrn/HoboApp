package com.lesson.hobo.data.googleMapsAPI;

public interface ApiListener<T> {
    void onSuccess(T response);

    void onError(Throwable throwable);
}
