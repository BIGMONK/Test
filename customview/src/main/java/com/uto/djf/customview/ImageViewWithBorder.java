package com.uto.djf.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by djf on 2016/11/25.
 */
public class ImageViewWithBorder extends ImageView {

    private TypedArray mTypedArray;
    private Paint paint;
    private int borderColor;
    private float borderCorner;
    private float borderWidth;
    private int mViewWidth;
    private int mViewHeight;

    public ImageViewWithBorder(Context context) {
        super(context);
    }

    public ImageViewWithBorder(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViewParameter(context, attrs);
    }

    public ImageViewWithBorder(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViewParameter(context, attrs);
    }

    private void initViewParameter(Context context, AttributeSet attrs) {
        mTypedArray = context.obtainStyledAttributes(attrs,
                R.styleable.ImageViewWithBorder);

        borderColor = mTypedArray.getColor(R.styleable.ImageViewWithBorder_borderColor, 0xffffff);
        borderCorner = mTypedArray.getDimension(R.styleable.ImageViewWithBorder_borderCorner, 2);
        borderWidth = mTypedArray.getDimension(R.styleable.ImageViewWithBorder_borderWidth, 2);
        mTypedArray.recycle();
        paint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewWidth = widthMeasureSpec;
        mViewHeight = heightMeasureSpec;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Path clipPath = new Path();
        int w = this.getWidth();
        int h = this.getHeight();
//        clipPath.addRoundRect(new RectF(0, 0, w, h), 100, 100, Path.Direction.CW);
        clipPath.addRoundRect(new RectF(0, 0, w, h), borderCorner, borderCorner, Path.Direction.CW);
        canvas.clipPath(clipPath);
        super.onDraw(canvas);
//        TODO 边框
        // 画边框
        Rect rec = canvas.getClipBounds();
        rec.bottom--;
        rec.right--;
        Paint paint = new Paint();
        paint.setColor(borderColor);   //颜色
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(borderWidth);
        canvas.drawRect(rec, paint);

    }
}














