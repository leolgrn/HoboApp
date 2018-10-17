package com.lesson.hobo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

public class HoboForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobo_form);

        Intent i = getIntent();
        Double latitude = i.getDoubleExtra("latitude", 1);
        Double longitude = i.getDoubleExtra("longitude", 1);
        Double radius = i.getDoubleExtra("radius", 1);

        Log.d(Constant.TAG, String.valueOf(latitude));
        Log.d(Constant.TAG, String.valueOf(longitude));
        Log.d(Constant.TAG, String.valueOf(radius));

    }
}
