package com.marathidevelopers.pharmabuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;



public class askForNotes extends AppCompatActivity {

    WebView asknote;

    String cookies="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_for_notes);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);





        asknote= (WebView)findViewById(R.id.asknote);
        asknote.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSfsKTZdf2HgCbg-zZ4jlpZrWzOKfOgnXNfrYD2cFXqkpwKi5g/viewform?usp=sf_link");
        asknote.setWebViewClient(new Client());
        WebSettings ws = asknote.getSettings();
        ws.setJavaScriptEnabled(true);
        asknote.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        asknote.clearCache(true);
        asknote.clearHistory();

        clearCookies();

        cookies = CookieManager.getInstance().getCookie("https://docs.google.com/forms/d/e/1FAIpQLSfsKTZdf2HgCbg-zZ4jlpZrWzOKfOgnXNfrYD2cFXqkpwKi5g/viewform?usp=sf_link");
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
}

