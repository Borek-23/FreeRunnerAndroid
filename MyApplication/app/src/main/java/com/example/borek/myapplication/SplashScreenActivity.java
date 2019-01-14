package com.example.borek.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

// This activity would display a splash screen for two seconds
// Unfortunately due to time constraints this was not implemented in the prototype

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }
}
