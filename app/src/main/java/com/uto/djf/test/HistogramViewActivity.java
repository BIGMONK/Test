package com.uto.djf.test;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.uto.djf.test.activity.BaseActivity;
import com.uto.djf.test.view.HistogramView;

import java.util.Random;

public class HistogramViewActivity extends BaseActivity {


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            hv6.setProgress(msg.getData().getDouble("progress"));
            hv1.setProgress(random.nextDouble());
            hv2.setProgress(random.nextDouble());
            hv3.setProgress(random.nextDouble());
            hv4.setProgress(random.nextDouble());
            hv5.setProgress(random.nextDouble());
            hv.setProgress(random.nextDouble());
        }
    };
    private HistogramView hv6;
    private HistogramView hv1;
    private HistogramView hv2;
    private HistogramView hv3;
    private HistogramView hv4;
    private HistogramView hv5;
    private Random random;
    private HistogramView hv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_histogram_view;
    }

    @Override
    protected void initView() {

        View v1 = findViewById(R.id.v1);
        ObjectAnimator.ofInt(new ViewWrapper(v1), "height", 30).setDuration(4000).start();

        View v2 = findViewById(R.id.v2);
        ObjectAnimator.ofInt(new ViewWrapper(v2), "width", 200).setDuration(4000).start();

        hv = (HistogramView) findViewById(R.id.hv);
        hv.setrateBackgroundColors(new int[]{
                Color.GREEN
                ,Color.BLUE
                ,Color.YELLOW
                ,Color.RED
        });
        hv.setOrientation(LinearLayout.VERTICAL);
        hv.setAnim(false);
        hv.setBlockCount(50);
        hv.setProgress(0.4);

        hv1 = (HistogramView) findViewById(R.id.hv1);
        hv1.setProgress(0.4);
        hv1.setRateBackgroundId(R.mipmap.ic_launcher);
        hv1.setOrientation(LinearLayout.VERTICAL);
        hv1.setAnim(false);
        hv1.setBlockCount(20);

        hv2 = (HistogramView) findViewById(R.id.hv2);
        hv2.setProgress(0.4);
        hv2.setOrientation(LinearLayout.VERTICAL);
        hv2.setAnim(false);
        hv2.setBlockCount(10);

        hv3 = (HistogramView) findViewById(R.id.hv3);
        hv3.setProgress(0.4);
        hv3.setRateBackgroundId(R.drawable.bg_heartbeat_lable);
        hv3.setOrientation(LinearLayout.VERTICAL);
        hv3.setAnim(false);
        hv3.setBlockCount(10);

        hv4 = (HistogramView) findViewById(R.id.hv4);
        hv4.setProgress(0.4);
        hv4.setOrientation(LinearLayout.VERTICAL);
        hv4.setAnim(false);
        hv4.setBlockCount(10);

        hv5 = (HistogramView) findViewById(R.id.hv5);
        hv5.setProgress(0.4);
        hv5.setOrientation(LinearLayout.VERTICAL);
        hv5.setAnim(false);

        hv6 = (HistogramView) findViewById(R.id.hv6);
        hv6.setProgress(0.4);
        hv6.setOrientation(LinearLayout.VERTICAL);
        hv6.setAnim(false);


        random = new Random();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    try {
                        Thread.sleep(1000);
                        Message message = new Message();
                        Bundle bundle = new Bundle();
                        bundle.putDouble("progress", random.nextDouble());
                        message.setData(bundle);
                        handler.sendMessage(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();


    }


    class ViewWrapper {
        private View mTarget;

        public ViewWrapper(View target) {
            mTarget = target;
        }

        public int getWidth() {
            return mTarget.getLayoutParams().width;
        }

        public void setWidth(int width) {
            mTarget.getLayoutParams().width = width;
            mTarget.requestLayout();
        }

        public int getHeight() {
            return mTarget.getLayoutParams().height;
        }

        public void setHeight(int height) {
            mTarget.getLayoutParams().height = height;
            mTarget.requestLayout();
        }

    }


    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
