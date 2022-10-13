package com.marathidevelopers.pharmabuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class otherapps extends AppCompatActivity {

    WebView app;
    String cookies="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otherapps);

        app= (WebView)findViewById(R.id.app);
        app.loadUrl("https://play.google.com/store/apps/dev?id=7639115298975832359");

        clearCookies();

        cookies = CookieManager.getInstance().getCookie("https://play.google.com/store/apps/dev?id=7639115298975832359");
        Log.d("cookies before clear = ", cookies+"");
    }

    public static void clearCookies() {
        CookieManager.getInstance().removeAllCookies(null);
        CookieManager.getInstance().flush();

    }
    }
