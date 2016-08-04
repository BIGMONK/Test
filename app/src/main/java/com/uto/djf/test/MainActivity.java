package com.uto.djf.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @BindView(R.id.btn_get_screen_parameter)
    Button btnGetScreenParameter;
    @BindView(R.id.btn_expend_listview)
    Button btnExpendListview;


    @Override
    int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    void initView() {

    }

    @Override
    void initListener() {

    }

    @Override
    void initData() {

    }


    @OnClick({R.id.btn_get_screen_parameter, R.id.btn_expend_listview})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_get_screen_parameter:
                startActivity(new Intent(this, ScreenMesureActivity.class));
                break;
            case R.id.btn_expend_listview:
                startActivity(new Intent(this, ExpendListViewActivity.class));
                break;


        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
