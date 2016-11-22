package com.uto.djf.test.activity;

import android.content.Intent;
import android.widget.Button;

import com.uto.djf.test.R;

import butterknife.BindView;
import butterknife.OnClick;

public class BActivity extends BaseActivity {


    @BindView(R.id.btn_start_c)
    Button btnStartC;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_b;
    }

    @Override
    protected void initView() {
        System.out.println("BActivity Create");
    }

    @Override
    protected void initListener() {
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("BActivity Destory");
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("BActivity Start");
    }


    @OnClick(R.id.btn_start_c)
    public void onClick() {
        startActivity(new Intent(this,CActivity.class));
    }
}
