package com.uto.djf.test.activity;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.uto.djf.test.R;
import com.uto.djf.test.utils.Config;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.OnClick;

public class NetResourceTestActivity extends BaseActivity {


    @BindView(R.id.imageview)
    ImageView imageview;
    @BindView(R.id.imageview2)
    ImageView imageview2;
    private Drawable drawable;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_net_resource_test;
    }

    @Override
    protected void initView() {

//        final String url = "http://i1.hexunimg.cn/2014-08-15/167580248.jpg";
        final String url = "http://pic105.nipic.com/file/20160725/8929440_123110597000_.jpg";

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    System.out.println("aaaaaaaaa");
                    drawable = Drawable.createFromStream(
                            ((InputStream) new URL(url).getContent()), null);
                    System.out.println("bbbbbbbbbbbbbb");
                    if (drawable != null) {
                        System.out.println("cccccccccccccc");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageview.setBackground(drawable);
                                System.out.println("ddddddddddddd");
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println(e.toString());
                }
            }
        }.start();
        final String url2 = url;
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    System.out.println("111111111");
                    URL url = new URL(url2);
                    HttpURLConnection conn = (HttpURLConnection) url
                            .openConnection();

                    conn.setRequestMethod("GET");

                    // 真实开发中，尽可能长一些，保证所有手机都可以连接到
                    conn.setConnectTimeout(2000);

                    int code = conn.getResponseCode();
                    if (code == 200) {
                        // 联网成功
                        InputStream inputStream = conn.getInputStream();
                        drawable = Drawable.createFromStream(inputStream, null);
                        System.out.println("2222222222222222");
                        if (drawable != null) {
                            System.out.println("33333333333333");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    imageview.setBackground(drawable);
                                    System.out.println("444444444444444");
                                }
                            });
                        }
                    } else {
                        System.out.println("555555555");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }


    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.imageview)
    public void onClick() {
    }
}
