package com.uto.djf.mylibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by djf on 2016/10/31.
 */
public class HistogramView extends View implements Runnable {
    private static final String TAG = HistogramView.class.getSimpleName();

    private int comWidth; //控件宽度
    private int comHeight;//控件高度

    private View rateView;//进度条

    private View rateTopView; //进度条顶部View

    private int[] rateBackgroundColors;//进图条背景颜色

    private int rateBackgroundId; //进图条背景图片id

    private Bitmap rataBackgroundBitmap;

    private boolean isHasRateTopView; //进度条顶部View

    private int rateHeight; //进度条的高

    private int rateWidth; //进度条的宽

    private int rateAnimValue;//进度条动画高度

    private int orientation; //设置柱状图方向

    private double progress;//设置进度  1为最大值

    private boolean isAnim = true; //是否动画显示统计条

    private Handler handler = new Handler();//动画handler

    private int animRate = 1; //动画速度   以每1毫秒计

    private int animTime = 1;//动画延迟执行时间

    private Canvas canvas;//画布
    private Paint paint;
    private float mCycleFactorW;

    public int getBlockCount() {
        return blockCount;
    }

    public void setBlockCount(int blockCount) {
        this.blockCount = blockCount;
    }

    private int blockCount;


    private static final int[] SECTION_COLORS = {
            0xff46b641,
            0xff23b2cf,
            0xffd29d28,
            0xffA72529
    };
    private float everyHeight;

    public HistogramView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public HistogramView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HistogramView(Context context) {
        super(context);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //初始化控件和进度的条相关参数
        comWidth = w;
        comHeight = h;
//        if(orientation== LinearLayout.HORIZONTAL){
//            rateWidth = (int) (w*progress);
//            rateHeight = h;
//        }else{
//            rateHeight = (int) (h*progress);
//            rateWidth = w;
//        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (orientation == LinearLayout.HORIZONTAL) {
            rateWidth = (int) (comWidth * progress);
            rateHeight = comHeight;
        } else {
            rateHeight = (int) (comHeight * progress);
            rateWidth = comWidth;
        }

        this.canvas = canvas;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        if (rateBackgroundColors == null) {
            int x = 0;
            if (progress <= 1.0f / 4) {
                x = 0;
            } else if (progress < 2.0f / 4) {
                x = 1;
            } else if (progress < 3.0f / 4) {
                x = 2;
            } else {
                x = 3;
            }
            LinearGradient lg = new LinearGradient(0, 0, 0, comHeight,
                    Color.WHITE, SECTION_COLORS[x], Shader.TileMode.CLAMP);
            Log.d(TAG, "onDraw  SECTION_COLORS[x]====" + SECTION_COLORS[x]);
            paint.setShader(lg);
            drawViewWithColor(paint, isAnim);

        } else if (rateBackgroundColors.length > 0) {
            int length = rateBackgroundColors.length;
            int x = (int) (progress * length);
            LinearGradient lg = new LinearGradient(0, 0, 0, comHeight,
                    Color.WHITE, rateBackgroundColors[x], Shader.TileMode.CLAMP);
            paint.setShader(lg);
            drawViewWithColor(paint, isAnim);
        } else if (rateBackgroundId != -1) {
            drawViewWithBitmap(paint, isAnim);
        }
        //前景画线
//        float everyHeight =  comHeight / (float)blockCount;
//        paint = new Paint();
//        paint.setStyle(Paint.Style.FILL_AND_STROKE);
//        paint.setColor(0xff21282f);
//        paint.setStrokeWidth(1f);
//        for (int i = 0; i < rateHeight / everyHeight; i++) {
//            canvas.drawLine(0, comHeight - i * everyHeight, comWidth, comHeight - i * everyHeight, paint);//画线
//        }


    }

    /**
     * drawViewWithColor:(绘制颜色进度条). <br/>
     *
     * @param paint
     * @param isAnim
     * @author msl
     * @since 1.0
     */
    private void drawViewWithColor(Paint paint, boolean isAnim) {
//        paint.setColor(Color.parseColor(rateBackgroundColors));
        Log.d(TAG, "rateBackgroundColors====" + rateBackgroundColors);
        if (isAnim) {
            handler.postDelayed(this, animTime);
            if (orientation == LinearLayout.HORIZONTAL) {//水平方向柱状图
                canvas.drawRect(0, 0, rateAnimValue, comHeight, paint);

            } else {//垂直方向柱状图
                canvas.drawRect(0, comHeight - rateAnimValue, comWidth, comHeight, paint);
            }
        } else {
            if (orientation == LinearLayout.HORIZONTAL) {//水平方向无动画柱状图
                canvas.drawRect(0, 0, rateWidth, comHeight, paint);
            } else {//垂直方向无动画柱状图

//                canvas.drawRect(0, comHeight - rateHeight, comWidth, comHeight, paint);

                // 将周期定为view总宽度
                mCycleFactorW = (float) (2 * Math.PI / comWidth);
                // 根据view总宽度得出所有对应的y值
                for (int i = 0; i < comWidth; i++) {
                   float mYPositions= (float) (2 * Math.sin(mCycleFactorW * i));
                    canvas.drawLine(i, comHeight -rateHeight- mYPositions, i,
                            comHeight,
                            paint);
                }
            }
        }

    }

    /**
     * drawViewWithBitmap:(绘制图片进度条). <br/>
     *
     * @param paint
     * @param isAnim
     * @author msl
     * @since 1.0
     */
    private void drawViewWithBitmap(Paint paint, boolean isAnim) {
        Log.d(TAG, "bitmap====" + rataBackgroundBitmap);
        RectF dst = null;
        if (isAnim) {
            handler.postDelayed(this, animTime);
            if (orientation == LinearLayout.HORIZONTAL) {//水平方向柱状图
                dst = new RectF(0, 0, rateAnimValue, comHeight);
                canvas.drawBitmap(rataBackgroundBitmap, null, dst, paint);
            } else {//垂直方向柱状图
                dst = new RectF(0, comHeight - rateAnimValue, comWidth, comHeight);
                canvas.drawBitmap(rataBackgroundBitmap, null, dst, paint);
            }
        } else {
            if (orientation == LinearLayout.HORIZONTAL) {//水平方向无动画柱状图
                dst = new RectF(0, 0, rateWidth, comHeight);
                canvas.drawBitmap(rataBackgroundBitmap, null, dst, paint);
            } else {//垂直方向无动画柱状图
                dst = new RectF(0, comHeight - rateHeight, comWidth, comHeight);
                canvas.drawBitmap(rataBackgroundBitmap, null, dst, paint);
            }
        }
    }


    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        if (progress > 1)
            this.progress = 1;
        else
            this.progress = progress;
        invalidate();
    }


    public View getRateView() {
        return rateView;
    }

    public void setRateView(View rateView) {
        this.rateView = rateView;
    }

    public int getRateHeight() {
        return rateHeight;
    }

    public void setRateHeight(int rateHeight) {
        this.rateHeight = rateHeight;
    }

    public int getRateWidth() {
        return rateWidth;
    }

    public void setRateWidth(int rateWidth) {
        this.rateWidth = rateWidth;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }


    public boolean isAnim() {
        return isAnim;
    }

    public void setAnim(boolean isAnim) {
        this.isAnim = isAnim;
    }

    public int getAnimRate() {
        return animRate;
    }

    public void setAnimRate(int animRate) {
        this.animRate = animRate;
    }

    public int[] getrateBackgroundColors() {
        return rateBackgroundColors;
    }

    public void setrateBackgroundColors(int[] rateBackgroundColors) {
        this.rateBackgroundColors = rateBackgroundColors;
        rateBackgroundId = -1;
        rataBackgroundBitmap = null;
    }


    public int getRateBackgroundId() {
        return rateBackgroundId;
    }

    public void setRateBackgroundId(int rateBackground) {
        this.rateBackgroundId = rateBackground;
        rataBackgroundBitmap = BitmapFactory.decodeResource(getResources(), rateBackgroundId);
        rateBackgroundColors = null;
    }

    /**
     * 刷新界面
     *
     * @see Runnable#run()
     */
    @Override
    public void run() {
        if (orientation == LinearLayout.HORIZONTAL && (rateAnimValue <= rateWidth)) {
            rateAnimValue += animRate;
            invalidate();
        } else if (orientation == LinearLayout.VERTICAL && (rateAnimValue <= rateHeight)) {
            rateAnimValue += animRate;
            invalidate();
        } else {
            handler.removeCallbacks(this);
            rateAnimValue = 0;
        }

    }


}
