package com.uto.djf.test.view;

/**
 * Created by djf on 2016/8/29.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.uto.djf.test.R;

public class RoundCornerImageView extends ImageView {

    private int round;
    private TypedArray typedArray;

    public RoundCornerImageView(Context context) {
        super(context);
    }

    public RoundCornerImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.RoundCornerImageView);
        round = typedArray.getInt(R.styleable.RoundCornerImageView_Round, 0);
    }

    public RoundCornerImageView(Context context, AttributeSet attrs,
                                int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Path clipPath = new Path();
        int w = this.getWidth();
        int h = this.getHeight();
//        clipPath.addRoundRect(new RectF(0, 0, w, h), 100, 100, Path.Direction.CW);
        //round 等于空间宽度一半  是圆形
        clipPath.addRoundRect(new RectF(0, 0, w, h), round, round, Path.Direction.CW);
        canvas.clipPath(clipPath);
        super.onDraw(canvas);

    }
}
