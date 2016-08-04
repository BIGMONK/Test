package com.uto.djf.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScreenMesureActivity extends BaseActivity {

    @BindView(R.id.tv_screen_width_and_heigh)
    TextView tvScreenWidthAndHeigh;
    @BindView(R.id.tv_screen_width_and_heigh2)
    TextView tvScreenWidthAndHeigh2;

    @Override
    int getLayoutId() {
        return R.layout.activity_screen_mesure;
    }

    @Override
    void initView() {

    }

    @Override
    void initListener() {

    }

    @Override
    void initData() {
        //方法一
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int screenWidth = display.getWidth();
        int screenHeight = display.getHeight();

        tvScreenWidthAndHeigh.setText("屏幕宽高" + screenWidth + "*" + screenHeight);

        // 方法2
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float width = dm.widthPixels;
        float height = dm.heightPixels;
        tvScreenWidthAndHeigh2.setText("屏幕宽高" + width + "*" + height + "\n\r\tDisplayMetrics" + dm.toString());
    }
}
