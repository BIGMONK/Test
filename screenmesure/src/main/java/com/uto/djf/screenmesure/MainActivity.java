package com.uto.djf.screenmesure;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    /**
     * 屏幕宽高
     */
    private TextView tvScreenWidthAndHeight;
    private TextView tvScreenWidthAndHeight2;
    private TextView tvLl4;
    private TextView tvLl5;
    private TextView tvLl3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hideNavigationBar();//隐藏底部虚拟按键
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏顶部信息栏

        setContentView(R.layout.activity_main);

        tvScreenWidthAndHeight = (TextView) findViewById(R.id.tvScreenWidthAndHeight);
        tvScreenWidthAndHeight2 = (TextView) findViewById(R.id.tvScreenWidthAndHeight2);
        tvLl4 = (TextView) findViewById(R.id.ll4);
        tvLl4.setText("点击获取宽高参数");
        tvLl4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvLl4.setText(tvLl4.getWidth() + "*" + tvLl4.getHeight());
            }
        });
        tvLl5 = (TextView) findViewById(R.id.ll5);
        tvLl5.setText("点击获取宽高参数");
        tvLl5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvLl5.setText(tvLl5.getWidth() + "*" + tvLl5.getHeight());
            }
        });
        tvLl3 = (TextView) findViewById(R.id.ll3);
        tvLl3.setText("点击获取宽高参数");
        tvLl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvLl3.setText(tvLl3.getWidth() + "*" + tvLl3.getHeight());
            }
        });

        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int screenWidth = display.getWidth();
        int screenHeight = display.getHeight();

        tvScreenWidthAndHeight.setText("屏幕宽高" + screenWidth + "*" + screenHeight);
        tvScreenWidthAndHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WindowManager windowManager = getWindowManager();
                Display display = windowManager.getDefaultDisplay();
                int screenWidth = display.getWidth();
                int screenHeight = display.getHeight();

            }
        });
        // 方法2
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float width = dm.widthPixels;
        float height = dm.heightPixels;
        tvScreenWidthAndHeight2.setText("屏幕宽高" + width + "*" + height + "\n\r\tDisplayMetrics" + dm.toString());
        tvScreenWidthAndHeight2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayMetrics dm = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(dm);
                float width = dm.widthPixels;
                float height = dm.heightPixels;
                tvScreenWidthAndHeight2.setText("屏幕宽高" + width + "*" + height + "\n\r\tDisplayMetrics" + dm.toString());
            }
        });
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

}
