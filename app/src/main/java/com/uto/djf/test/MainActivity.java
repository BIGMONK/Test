package com.uto.djf.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.uto.djf.test.recyclerview.RecyclerViewActivity;
import com.uto.djf.test.screenmesure.ScreenMesureActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @BindView(R.id.btn_get_screen_parameter)
    Button btnGetScreenParameter;
    @BindView(R.id.btn_expend_listview)
    Button btnExpendListview;
    @BindView(R.id.btn_recycler)
    Button btnRecycler;
    @BindView(R.id.btn_image_identify)
    Button btnImageIdentify;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.btn_get_screen_parameter, R.id.btn_expend_listview,
            R.id.btn_recycler, R.id.btn_image_identify})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get_screen_parameter:
                startActivity(new Intent(this, ScreenMesureActivity.class));
                break;
            case R.id.btn_expend_listview:
                startActivity(new Intent(this, ExpandableListViewActivity.class));
                break;

            case R.id.btn_recycler:
                startActivity(new Intent(this, RecyclerViewActivity.class));
                break;
            case R.id.btn_image_identify:
                startActivity(new Intent(this, ImageIdentifyActivity.class));
                break;


        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick(R.id.btn_image_identify)
    public void onClick() {
    }
}
