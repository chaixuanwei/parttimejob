package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.frame.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebviewActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.webview)
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        Intent mIntent = getIntent();
        String mUrl = mIntent.getStringExtra("url");
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl(mUrl);
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
