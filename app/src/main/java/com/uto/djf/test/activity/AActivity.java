package com.uto.djf.test.activity;

import android.content.Intent;
import android.widget.Button;

import com.uto.djf.test.R;

import butterknife.BindView;
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
        Intent intent=new Intent(this,BActivity.class);
        intent.putExtra("obj",999);
        startActivity(intent);
    }
    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("AActivity Start");
    }
}
