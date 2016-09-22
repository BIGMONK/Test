package com.uto.djf.test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AActivity extends BaseActivity {

    @BindView(R.id.btn_start_b)
    Button btnStartB;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_a;
    }

    @Override
    protected void initView() {
        System.out.println("AActivity Create");
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
        System.out.println("AActivity Destory");
    }


    @OnClick(R.id.btn_start_b)
    public void onClick() {
        startActivity(new Intent(this,BActivity.class));
    }
    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("AActivity Start");
    }
}
