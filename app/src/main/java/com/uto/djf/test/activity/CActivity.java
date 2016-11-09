package com.uto.djf.test.activity;

import android.content.Intent;
import android.widget.Button;

import com.uto.djf.test.R;

import butterknife.BindView;
import butterknife.OnClick;

public class CActivity extends BaseActivity {


    @BindView(R.id.btn_start_a)
    Button btnStartA;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_c;
    }

    @Override
    protected void initView() {
        System.out.println("CActivity Create");
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
        System.out.println("CActivity Destory");
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("CActivity Start");
    }



    @OnClick(R.id.btn_start_a)
    public void onClick() {
        startActivity(new Intent(this,AActivity.class));
    }
}
