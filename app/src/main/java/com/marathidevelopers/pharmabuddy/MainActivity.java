package com.marathidevelopers.pharmabuddy;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import com.google.android.material.navigation.NavigationView;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.IUnityBannerListener;
import com.unity3d.services.banners.UnityBanners;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;

    private String GameID = "4379091";
    private boolean testMode = false;
    private String bannerAdPlacement = "Banner_Android";


    CardView box1;
    CardView box2;
    CardView box3;
    CardView box4;
    private long backPressedTime;
    private Toast backToast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        UnityAds.initialize(MainActivity.this, GameID, testMode);


        IUnityBannerListener iUnityBannerListener = new IUnityBannerListener() {
            @Override
            public void onUnityBannerLoaded(String s, View view) {
                ((ViewGroup) findViewById(R.id.bannerAdLayout)).removeView(view);
                ((ViewGroup) findViewById(R.id.bannerAdLayout)).addView(view);

            }

            @Override
            public void onUnityBannerUnloaded(String s) {

            }

            @Override
            public void onUnityBannerShow(String s) {

            }

            @Override
            public void onUnityBannerClick(String s) {

            }

            @Override
            public void onUnityBannerHide(String s) {

            }

            @Override
            public void onUnityBannerError(String s) {

            }
        };

        UnityBanners.setBannerListener(iUnityBannerListener);
        if (UnityAds.isInitialized()) {
            UnityBanners.loadBanner(MainActivity.this, bannerAdPlacement);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                }
            }, 2000);
        } else {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            UnityBanners.loadBanner(MainActivity.this, bannerAdPlacement);
                        }
                    }, 2000);
                }
            }, 2000);
        }

        ConnectivityManager connectivityManager= (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= connectivityManager.getActiveNetworkInfo();
        if (networkInfo==null || !networkInfo.isConnected() || !networkInfo.isAvailable()){
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.alert_dialog);
            dialog.setCancelable(false);
            dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;

            Button button= dialog.findViewById(R.id.btn);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recreate();
                }
            });
            dialog.show();
        }






        box1 = findViewById(R.id.box1);
        box2 = findViewById(R.id.box2);
        box3 = findViewById(R.id.box3);
        box4 = findViewById(R.id.box7);

        box1.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,listbpharm.class)));


        box2.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,listdpharm.class)));


        box3.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,askForNotes.class)));


        box4.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,aboutUs.class)));



        drawerLayout = findViewById(R.id.nav_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container,new homefragment());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_draw_open,R.string.navigation_draw_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState==null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container,new homefragment()).commit();
            navigationView.setCheckedItem(R.id.home);
        }

    }


    private void save_id(int id) {
        SharedPreferences preferences = getSharedPreferences("SAVING",MODE_PRIVATE);
        SharedPreferences.Editor editor= preferences.edit();
        editor.putInt("mID",id);
        editor.apply();
    }
    private int load_id(){
        SharedPreferences preferences = getSharedPreferences("SAVING",MODE_PRIVATE);
        return preferences.getInt("mID",0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.home:
                break;

            case R.id.gpat:
                Intent intent1 = new Intent(this, gpat.class);
                startActivity(intent1);
                break;

            case R.id.share:
                Intent intent2 = new Intent(this, otherapps.class);
                startActivity(intent2);
                break;
            case R.id.share1:
                Intent shareintent = new Intent();
                shareintent.setAction(Intent.ACTION_SEND);
                shareintent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.marathidevelopers.pharmabuddy");
                shareintent.setType("text/plain");
                startActivity(Intent.createChooser(shareintent, "Share Via"));
                break;

            case R.id.Rateus:
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" + getPackageName())));
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
                }
                break;

            case R.id.instagram:
                Intent intent3 = new Intent(this, Insta.class);
                startActivity(intent3);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast= Toast.makeText(getBaseContext(),"Press Back Again To Exit",Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime= System.currentTimeMillis();

    }

}
