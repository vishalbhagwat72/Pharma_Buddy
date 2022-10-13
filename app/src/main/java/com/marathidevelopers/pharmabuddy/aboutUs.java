package com.marathidevelopers.pharmabuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebView;


public class aboutUs extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);



        WebView aboutus = findViewById(R.id.aboutus);
        aboutus.loadUrl("file:///android_asset/newabout");
    }
}