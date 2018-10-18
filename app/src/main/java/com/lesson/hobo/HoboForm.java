package com.lesson.hobo;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;

import com.google.android.gms.maps.model.LatLng;
import com.lesson.hobo.data.googleMapsAPI.ApiListener;
import com.lesson.hobo.data.googleMapsAPI.GoogleMapsAPIProvider;
import com.lesson.hobo.data.model.Hobo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HoboForm extends AppCompatActivity {

    /*@BindView(R.id.hobo_birth_date) EditText birth_date;*/

    @BindView(R.id.hobo_forname) EditText hobo_forname;
    @BindView(R.id.hobo_lastname) EditText hobo_lastname;
    @BindView(R.id.description) EditText description;
    @BindView(R.id.hobo_f) RadioButton hobo_f;
    @BindView(R.id.hobo_m) RadioButton hobo_m;
    @BindView(R.id.hobo_is_self) CheckBox hobo_is_self;
    @BindView(R.id.save_button) Button save_button;
    @BindView(R.id.voile) ConstraintLayout voile;
    @BindView(R.id.success_block) ConstraintLayout success_block;
    @BindView(R.id.fail_block) ConstraintLayout fail_block;
    @BindView(R.id.progressBar) ProgressBar progressBar;

    private Double latitude;
    private Double longitude;

    private Hobo hobo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobo_form);

        ButterKnife.bind(this);

        Intent i = getIntent();
        latitude = i.getDoubleExtra("latitude", 1);
        longitude = i.getDoubleExtra("longitude", 1);
        Double radius = i.getDoubleExtra("radius", 1);

        voile.setVisibility(View.INVISIBLE);
        success_block.setVisibility(View.INVISIBLE);
        fail_block.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(description.getText())){
                    description.setError("Ce champs est obligatoire");
                } else {
                    String gender;
                    gender = hobo_f.isChecked() ? "F" : "M";
                    hobo = new Hobo(String.valueOf(hobo_lastname.getText()), String.valueOf(hobo_forname.getText()), latitude, longitude, gender, String.valueOf(description.getText()), hobo_is_self.isChecked());
                    GoogleMapsAPIProvider googleMapsAPIProvider = new GoogleMapsAPIProvider();
                    progressBar.setVisibility(View.VISIBLE);
                    voile.setVisibility(View.VISIBLE);
                    googleMapsAPIProvider.postHobo(hobo, new ApiListener<Object>() {
                        @Override
                        public void onSuccess(Object response) {
                            success_block.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.INVISIBLE);
                            setTimeout(() -> {
                                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                                i.putExtra("isAlreadyLaunched", true);
                                startActivity(i);
                            }, 3000);
                        }

                        @Override
                        public void onError(Throwable throwable) {
                            fail_block.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.INVISIBLE);
                            setTimeout(() -> {
                                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                                startActivity(i);
                            }, 3000);
                        }
                    });
                }
            }
        });

        hobo_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hobo_m.setChecked(false);
            }
        });

        hobo_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hobo_f.setChecked(false);
            }
        });

    }

    public static void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }
}
