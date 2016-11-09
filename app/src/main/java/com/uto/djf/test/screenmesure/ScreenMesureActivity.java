package com.uto.djf.test.screenmesure;

import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

import com.uto.djf.test.activity.BaseActivity;
import com.uto.djf.test.R;

import butterknife.BindView;

public class ScreenMesureActivity extends BaseActivity {

    @BindView(R.id.tv_screen_width_and_heigh)
    TextView tvScreenWidthAndHeigh;
    @BindView(R.id.tv_screen_width_and_heigh2)
    TextView tvScreenWidthAndHeigh2;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_screen_mesure;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
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
