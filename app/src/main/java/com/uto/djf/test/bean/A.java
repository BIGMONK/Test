package com.uto.djf.test.bean;

/**
 * Created by djf on 2016/11/22.
 */
public class A {
    public String show(D obj){
        return ("A and D");
    }
    public String show(A obj){
        return ("A and A");
    }
}
