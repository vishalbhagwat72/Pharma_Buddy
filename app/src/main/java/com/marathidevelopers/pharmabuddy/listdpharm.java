package com.marathidevelopers.pharmabuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.IUnityBannerListener;
import com.unity3d.services.banners.UnityBanners;


public class listdpharm extends AppCompatActivity {


    CardView box7;
    CardView box8;
    CardView box9;
    CardView box10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdpharm);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        box7 = findViewById(R.id.box7);
        box8 = findViewById(R.id.box8);
        box9 = findViewById(R.id.box9);
        box10 = findViewById(R.id.box10);


        box7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(listdpharm.this, dnotes.class));
            }

        });

        box8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(listdpharm.this, dbooks.class));
            }

        });

        box9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(listdpharm.this, dmcq.class));
            }

        });

        box10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(listdpharm.this, dqp.class));
            }

        });

    }

}