package com.uto.djf.test.bean;

/**
 * Created by djf on 2016/11/22.
 */
public class B extends A {
    public String show(B obj){
        return ("B and B");
    }
    public String show(A obj){
        return ("B and A");
    }
}
