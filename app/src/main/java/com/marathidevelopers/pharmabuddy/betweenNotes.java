package com.marathidevelopers.pharmabuddy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;


public class betweenNotes extends AppCompatActivity {

    CardView box1;
    CardView box2;
    CardView box3;
    CardView box4;
    CardView box5;
    CardView box6;
    CardView box7;
    CardView box8;

//    private String GameID = "4379091";
//    private boolean testMode = false;
//    private String interstitialAdPlacement = "Interstitial_Android";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_between_notes);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        UnityAds.initialize(betweenNotes.this, GameID, testMode);
//        IUnityAdsListener unityAdsListener = new IUnityAdsListener() {
//            @Override
//            public void onUnityAdsReady(String s) {
//            }
//
//            @Override
//            public void onUnityAdsStart(String s) {
//            }
//
//            @Override
//            public void onUnityAdsFinish(String s, UnityAds.FinishState finishState) {
//            }
//
//            @Override
//            public void onUnityAdsError(UnityAds.UnityAdsError unityAdsError, String s) {
//            }
//        };
//
//        UnityAds.setListener(unityAdsListener);
//
//        if (UnityAds.isInitialized()) {
//            UnityAds.load(interstitialAdPlacement);
//            Handler handler = new Handler();
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    DisplayInterstitialAd();
//                }
//            }, 2000);
//        } else {
//            final Handler handler = new Handler();
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    UnityAds.load(interstitialAdPlacement);
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            DisplayInterstitialAd();
//                        }
//                    }, 2000);
//                }
//            }, 2000);
//        }




        box1= findViewById(R.id.box1);
        box2= findViewById(R.id.box2);
        box3= findViewById(R.id.box3);
        box4= findViewById(R.id.box4);
        box5= findViewById(R.id.box5);
        box6= findViewById(R.id.box6);
        box7= findViewById(R.id.box7);
        box8= findViewById(R.id.box8);

        box1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                    startActivity(new Intent(betweenNotes.this,sem1.class));
                }
        });


        box2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    startActivity(new Intent(betweenNotes.this,sem2.class));
                }
        });


        box3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    startActivity(new Intent(betweenNotes.this,sem3.class));
                }
        });

        box4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    startActivity(new Intent(betweenNotes.this,sem4.class));
                }
        });

        box5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    startActivity(new Intent(betweenNotes.this,sem5.class));
                }
        });

        box6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                    startActivity(new Intent(betweenNotes.this,sem6.class));
                }
        });

        box7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    startActivity(new Intent(betweenNotes.this,sem7.class));
                }
        });

        box8.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                    startActivity(new Intent(betweenNotes.this,sem8.class));
                }
        });

    }
//    private void DisplayInterstitialAd() {
//        if (UnityAds.isReady(interstitialAdPlacement)) {
//            UnityAds.show(betweenNotes.this, interstitialAdPlacement);
//        }
//    }

}