package com.uto.djf.test;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.uto.djf.test.view.MiniMapView;
import com.uto.djf.test.view.RoundProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomViewActivity extends BaseActivity {


    @BindView(R.id.activity_circle_image_view)
    LinearLayout activityCircleImageView;
    @BindView(R.id.minimapview)
    MiniMapView minimapview;
    @BindView(R.id.roundpeogressbar)
    RoundProgressBar roundpeogressbar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_circle_image_view;
    }

    @Override
    protected void initView() {
        minimapview.loadObjFile("minimap/multi_person_minimap_cuanzang.obj");

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
