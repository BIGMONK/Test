package com.uto.djf.test.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/7/24 0024 14:01.
 * 工程名称： VRAutoCycling
 * 包名称：   com.ut.vrautocycling.utils
 * 文件名称： ToastUtils
 */
public class ToastUtils {
    private static Toast toast;

    public static void SimpleToast(Context context, String string) {
        if (toast == null) {
            synchronized (ToastUtils.class) {
                if (toast == null) {
                    toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
                } else {
                    toast.setText(string);
                }
            }
        }
        toast.show();
    }

}
