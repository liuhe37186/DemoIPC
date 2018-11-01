package com.liuhe.demoipc;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class WebActivity extends AppCompatActivity {

    WebView webView;

//    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = findViewById(R.id.web_view);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);

        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String title = bundle.getString("title");
            String url = bundle.getString("url");
            //设置标题
            //setTitleText(title);
            webView.loadUrl(url);
        }else {
            webView.loadUrl("file:///android_asset/www/minfazongze.html");

        }


//        tv = findViewById(R.id.tv);


//        String s = getAssetsFileString("xianfa.html");
//        tv.setText(Html.fromHtml(s));
    }

    private String getAssetsFileString(String name) {
        AssetManager am = getAssets();
        String json = "";
        try {
            InputStream inputStream = am.open(name);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return json;
    }


}
