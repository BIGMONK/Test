package com.uto.djf.test.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.uto.djf.test.R;
import com.uto.djf.test.bean.A;
import com.uto.djf.test.bean.B;
import com.uto.djf.test.bean.C;
import com.uto.djf.test.bean.D;

public class PolymorphismTest extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_polymorphism_test;
    }

    @Override
    protected void initView() {
        A a1 = new A();
        A a2 = new B();
        B b = new B();
        C c = new C();
        D d = new D();

        Log.d(TAG, a1.show(b));
        Log.d(TAG, a1.show(c));
        Log.d(TAG, a1.show(d));
        Log.d(TAG, a2.show(b));
        Log.d(TAG, a2.show(c));
        Log.d(TAG, a2.show(d));
        Log.d(TAG, b.show(b));
        Log.d(TAG, b.show(c));
        Log.d(TAG, b.show(d));
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
