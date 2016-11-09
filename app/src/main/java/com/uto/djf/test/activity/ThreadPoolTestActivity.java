package com.uto.djf.test.activity;

import android.view.View;
import android.widget.Button;

import com.uto.djf.test.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;

public class ThreadPoolTestActivity extends BaseActivity {

    @BindView(R.id.btn_CachedThreadPool)
    Button btnCachedThreadPool;
    @BindView(R.id.btn_FixedThreadPool)
    Button btnFixedThreadPool;
    @BindView(R.id.btn_ScheduledThreadPool)
    Button btnScheduledThreadPool;
    @BindView(R.id.btn_SingleThreadExecutor)
    Button btnSingleThreadExecutor;
    private ExecutorService singleThreadExecutor;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_thread_pool_test;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.btn_CachedThreadPool, R.id.btn_FixedThreadPool, R.id.btn_ScheduledThreadPool, R.id.btn_SingleThreadExecutor})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_CachedThreadPool:
                final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
                for (int i = 0; i < 10; i++) {
                    final int index = i;
                    cachedThreadPool.execute(new Runnable() {
                        public void run() {
                            System.out.println(index+"  POOL 0   "+cachedThreadPool.toString());

                            try {
                                Thread.sleep(index * 1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println(index+"  POOL 1   "+cachedThreadPool.toString());

                        }
                    });
                }
                break;
            case R.id.btn_FixedThreadPool:
                final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
                for (int i = 0; i < 10; i++) {
                    final int index = i;
                    fixedThreadPool.execute(new Runnable() {
                        public void run() {
                            try {
                                System.out.println(index+"  POOL 0   "+fixedThreadPool.toString());
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                break;
            case R.id.btn_ScheduledThreadPool:
                final ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
                scheduledThreadPool.schedule(new Runnable() {
                    public void run() {
                        System.out.println("delay 2 seconds"+"  POOL 0   "+scheduledThreadPool.toString());
                    }
                }, 2, TimeUnit.SECONDS);

                scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
                    public void run() {
                        System.out.println("delay 2 seconds, and excute every 3 seconds"+"  POOL 0   "+scheduledThreadPool.toString());
                    }
                }, 2, 3, TimeUnit.SECONDS);
                break;
            case R.id.btn_SingleThreadExecutor:
                singleThreadExecutor = Executors.newSingleThreadExecutor();
                for (int i = 0; i < 10; i++) {
                    final int index = i;
                    singleThreadExecutor.execute(new Runnable() {
                        public void run() {
                            try {
                                System.out.println(index+"  POOL 0   "+ singleThreadExecutor.toString());
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        singleThreadExecutor.shutdown();

    }

}
