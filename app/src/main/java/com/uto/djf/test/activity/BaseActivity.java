package com.uto.djf.test.activity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.uto.djf.test.R;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;

/**
 * Created by djf on 2016/8/4.
 */
public abstract class BaseActivity extends AutoLayoutActivity {
    String TAG = this.getClass().getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,TAG+"     onCreate");
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
    protected abstract int getLayoutId();

    /**
     * 执行findViewById操作
     */
    protected abstract void initView();

    /**
     * 注册监听器、适配器
     */
    protected abstract void initListener();

    /**
     * 获取数据，初始化界面
     */
    protected abstract void initData();
    private Toast toast;
    public void SimpleToast(String string) {
        if (toast == null) {
            toast = Toast.makeText(this, string, Toast.LENGTH_SHORT);
        } else {
            toast.setText(string);
        }
        toast.show();
    }

}
