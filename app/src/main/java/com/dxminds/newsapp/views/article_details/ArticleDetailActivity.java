package com.dxminds.newsapp.views.article_details;


import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dxminds.newsapp.R;

public class ArticleDetailActivity extends AppCompatActivity {

    WebView webview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);
        initViews();
        getData();
        setClickListeners();
        callApi();
    }

    private void initViews() {
        webview = findViewById(R.id.webview);
    }

    private void getData() {
        String url = getIntent().getStringExtra("url");
        webview.setWebViewClient(new WebViewClient());
        WebSettings settings = webview.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        webview.loadUrl(url);
    }

    private void setClickListeners() {

    }

    private void callApi() {

    }
}


