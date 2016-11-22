package com.uto.djf.test.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.uto.djf.test.R;
import com.uto.djf.test.bean.A;
import com.uto.djf.test.bean.B;
import com.uto.djf.test.bean.C;
import com.uto.djf.test.bean.D;

public class PolymorphismTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polymorphism_test);

        A a1 = new A();
        A a2 = new B();
        B b = new B();
        C c = new C();
        D d = new D();

        System.out.println(a1.show(b));
        System.out.println(a1.show(c));
        System.out.println(a1.show(d));
        System.out.println(a2.show(b));
        System.out.println(a2.show(c));
        System.out.println(a2.show(d));
        System.out.println(b.show(b));
        System.out.println(b.show(c));
        System.out.println(b.show(d));

    }
}
