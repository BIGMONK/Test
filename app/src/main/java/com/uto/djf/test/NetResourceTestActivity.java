package com.uto.djf.test;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.uto.djf.test.utils.Config;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NetResourceTestActivity extends BaseActivity {


    @BindView(R.id.imageview)
    ImageView imageview;
    private Drawable drawable;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_net_resource_test;
    }

    @Override
    protected void initView() {

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    System.out.println("aaaaaaaaa");
                    drawable = Drawable.createFromStream(
                            ((InputStream) new URL(Config.HTTP_URL + Config.EXTEND_URL).getContent()), null);
                    System.out.println("bbbbbbbbbbbbbb");
                    if (drawable!=null){
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
