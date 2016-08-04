package com.uto.djf.test;

import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;

/**
 * Created by djf on 2016/8/4.
 */
public abstract class BaseActivity extends AutoLayoutActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //隐藏NavigationBar
        hideNavigationBar();
        this.setContentView(getLayoutId());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        ButterKnife.bind(this);
        initView();
        initListener();
        initData();
        regCommonBtn();


    }

    /**
     * 在多个界面间都存在的按钮，统一处理掉
     */
    public void regCommonBtn() {
        Button mBack = (Button) findViewById(R.id.main_back);

        if (mBack != null) {
            mBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    /**
     * 隐藏NavigationBar
     */
    private void hideNavigationBar() {

        int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
        int newUiOptions = uiOptions;
        boolean isImmersiveModeEnabled =
                ((uiOptions | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) == uiOptions);

        // Navigation bar hiding:  Backwards compatible to ICS.
        if (Build.VERSION.SDK_INT >= 14) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }

        // Status bar hiding: Backwards compatible to Jellybean
        if (Build.VERSION.SDK_INT >= 16) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        }

        if (Build.VERSION.SDK_INT >= 18) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        }

        getWindow().getDecorView().setSystemUiVisibility(newUiOptions);
    }

    /**
     * 返回当前Activity使用的布局id
     */
    abstract int getLayoutId();

    /**
     * 执行findViewById操作
     */
    abstract void initView();

    /**
     * 注册监听器、适配器
     */
    abstract void initListener();

    /**
     * 获取数据，初始化界面
     */
    abstract void initData();

}