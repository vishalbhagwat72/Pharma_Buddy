package com.marathidevelopers.pharmabuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

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
import android.util.Log;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class gpat extends AppCompatActivity {

    WebView gpat;
    String cookies="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpat);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);




        gpat= (WebView)findViewById(R.id.gpat);
        gpat.loadUrl("https://drive.google.com/drive/folders/1xq2SEPLyJh3G-DHCDhqhByPWKmgwYah6?usp=sharing");
        gpat.setWebViewClient(new gpat.Client());
        WebSettings ws = gpat.getSettings();
        ws.setJavaScriptEnabled(true);
        gpat.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        gpat.clearCache(true);
        gpat.clearHistory(); clearCookies();

        cookies = CookieManager.getInstance().getCookie("https://drive.google.com/drive/folders/1xq2SEPLyJh3G-DHCDhqhByPWKmgwYah6?usp=sharing");
        Log.d("cookies before clear = ", cookies+"");
    }

    public static void clearCookies() {
        CookieManager.getInstance().removeAllCookies(null);
        CookieManager.getInstance().flush();




    }

    private class Client extends WebViewClient {
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
        this.gpat.setDownloadListener(new DownloadListener() {
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
                        gpat.this.getSystemService("download")).enqueue(request);
                Toast.makeText(getApplicationContext(), "Downloading Started", Toast.LENGTH_SHORT).show();
                gpat.this.registerReceiver(new BroadcastReceiver() {
                                               public void onReceive(Context context, Intent intent) {
                                                   Toast.makeText(getApplicationContext(), "Download Completed", Toast.LENGTH_SHORT).show();
                                                   gpat.this.unregisterReceiver(this);
                                               }
                                           },
                        new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
            }
        });
//
    }}
