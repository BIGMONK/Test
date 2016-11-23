package com.uto.djf.test.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.uto.djf.test.R;
import com.uto.djf.test.utils.TransformUtils;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class H5Activity extends BaseActivity {


    @BindView(R.id.webview)
    WebView webview;
    private int[] heartbeatArray;
    private Random mRandom;
    private StringBuffer heartbeatString;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_h5;
    }

    @Override
    protected void initView() {
        mRandom = new Random();
        heartbeatArray = new int[30];
        WebSettings mWebViewForH5Settings = webview.getSettings();
        mWebViewForH5Settings.setJavaScriptEnabled(true);
        String h5Url = "file:///android_asset/h5/index.html";
        webview.loadUrl(h5Url);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                handler.sendEmptyMessage(0);
            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            for (int i = 0; i < 30; i++) {
                heartbeatArray[i] = mRandom.nextInt(200);
            }

            System.out.println("LOAD     " + TransformUtils.ArrayToString(heartbeatArray));

//            webview.loadUrl("javascript:show('[171,10,128,155,135,180,123,135,175,190,15,180,5,65,70,75,80,25,130,20,160,165,170,30,50,100,140,185,150,125]')");
            webview.loadUrl("javascript:show('" + TransformUtils.ArrayToString(heartbeatArray) + "')");

            this.sendEmptyMessageDelayed(0, 1000);
        }
    };


    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

}
