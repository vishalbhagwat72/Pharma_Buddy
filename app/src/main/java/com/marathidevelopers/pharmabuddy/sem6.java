package com.marathidevelopers.pharmabuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;


public class sem6 extends AppCompatActivity {

    WebView sem6;
    String cookies="";

    private String GameID = "4379091";
    private boolean testMode = false;
    private String interstitialAdPlacement = "Interstitial_Android";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem6);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        UnityAds.initialize(sem6.this, GameID, testMode);
        IUnityAdsListener unityAdsListener = new IUnityAdsListener() {
            @Override
            public void onUnityAdsReady(String s) {
            }

            @Override
            public void onUnityAdsStart(String s) {
            }

            @Override
            public void onUnityAdsFinish(String s, UnityAds.FinishState finishState) {
            }

            @Override
            public void onUnityAdsError(UnityAds.UnityAdsError unityAdsError, String s) {
            }
        };

        UnityAds.setListener(unityAdsListener);

        if (UnityAds.isInitialized()) {
            UnityAds.load(interstitialAdPlacement);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    DisplayInterstitialAd();
                }
            }, 1000);
        } else {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    UnityAds.load(interstitialAdPlacement);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            DisplayInterstitialAd();
                        }
                    }, 1000);
                }
            }, 1000);
        }


        sem6= (WebView)findViewById(R.id.sem6);
        sem6.loadUrl("https://drive.google.com/drive/folders/1_jaPrX8tyb41ec17dLHhcK7LXGzKBgJp?usp=sharing");
        sem6.setWebViewClient(new sem6.Client());
        WebSettings ws = sem6.getSettings();
        ws.setJavaScriptEnabled(true);
        sem6.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        sem6.clearCache(true);
        sem6.clearHistory();

        clearCookies();

        cookies = CookieManager.getInstance().getCookie("https://drive.google.com/drive/folders/1_jaPrX8tyb41ec17dLHhcK7LXGzKBgJp?usp=sharing");
        Log.d("cookies before clear = ", cookies+"");
    }

    public static void clearCookies() {
        CookieManager.getInstance().removeAllCookies(null);
        CookieManager.getInstance().flush();



    }

    static class Client extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }


    public void onStart() {
        super.onStart();
        this.sem6.setDownloadListener(new DownloadListener() {
            @SuppressLint("WrongConstant")
            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(str));
                request.addRequestHeader("cookie", CookieManager.getInstance().getCookie(str));
                request.addRequestHeader("User-Agent", str2);
                request.setDescription("Downloading file...");
                request.setTitle(URLUtil.guessFileName(str, str3, str4));
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(1);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(str, str3, str4));
                ((DownloadManager)
                        sem6.this.getSystemService("download")).enqueue(request);
                Toast.makeText(getApplicationContext(), "Downloading Started", Toast.LENGTH_SHORT).show();
                sem6.this.registerReceiver(new BroadcastReceiver() {
                                               public void onReceive(Context context, Intent intent) {
                                                   Toast.makeText(getApplicationContext(), "Download Completed", Toast.LENGTH_SHORT).show();
                                                   sem6.this.unregisterReceiver(this);
                                               }
                                           },
                        new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
            }
        });



    }
    private void DisplayInterstitialAd() {
        if (UnityAds.isReady(interstitialAdPlacement)) {
            UnityAds.show(sem6.this, interstitialAdPlacement);
        }
    }}