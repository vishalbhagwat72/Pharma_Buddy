package com.marathidevelopers.pharmabuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.WebView;

public class Insta extends AppCompatActivity {

    WebView app;
    String cookies="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insta);
        app= (WebView)findViewById(R.id.insta);
        app.loadUrl("https://instagram.com/pharma_buddy_official?igshid=YmMyMTA2M2Y=");

        clearCookies();

        cookies = CookieManager.getInstance().getCookie("https://instagram.com/pharma_buddy_official?igshid=YmMyMTA2M2Y=");
        Log.d("cookies before clear = ", cookies+"");
    }

    public static void clearCookies() {
        CookieManager.getInstance().removeAllCookies(null);
        CookieManager.getInstance().flush();

    }
}
