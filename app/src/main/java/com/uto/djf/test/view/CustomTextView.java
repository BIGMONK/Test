package com.uto.djf.test.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by djf on 2016/12/1.
 */
public class CustomTextView extends TextView {

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setTypeface(Typeface tf) {
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/maozedong.ttf");
        if (typeface != null) {
            super.setTypeface(typeface);
        } else {
            super.setTypeface(tf);
            Log.d(this.getClass().getSimpleName(), "获取自定义字体资源失败");
        }
    }

    @Override
    public void setTypeface(Typeface tf, int style) {
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/maozedong.ttf");
        if (typeface != null) {
            super.setTypeface(typeface);
        } else {
            super.setTypeface(tf);
            Log.d(this.getClass().getSimpleName(), "获取自定义字体资源失败");
        }
    }
}
