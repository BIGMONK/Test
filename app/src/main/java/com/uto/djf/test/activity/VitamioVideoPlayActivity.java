package com.uto.djf.test.activity;

import android.graphics.PixelFormat;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.uto.djf.test.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;

public class VitamioVideoPlayActivity extends BaseActivity implements SurfaceHolder.Callback, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener {


    @BindView(R.id.surface_player)
    SurfaceView surfacePlayer;
    @BindView(R.id.btn_speed_decrease)
    Button btnSpeedDecrease;
    @BindView(R.id.btn_speed_increase)
    Button btnSpeedIncrease;
    private SurfaceHolder holder;
    private MediaPlayer mMediaPlayer;
    private float speed = 1.0f;
    String TAG = this.getClass().getSimpleName();
    private String path;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_vitamio_audio_play;
    }

    @Override
    protected void initView() {
        Vitamio.initialize(VitamioVideoPlayActivity.this);
        path = "http://192.168.1.37:8080/km1930/resources/single/1000/playvideo1000.mp4";
        holder = surfacePlayer.getHolder();
        holder.addCallback(this);
        holder.setFormat(PixelFormat.RGBA_8888);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            playVideo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void playVideo() throws IOException {

        // Create a new media player and set the listeners
        mMediaPlayer = new MediaPlayer(this);
        mMediaPlayer.setDataSource(path);
        mMediaPlayer.setDisplay(holder);
        mMediaPlayer.prepareAsync();
        mMediaPlayer.setOnBufferingUpdateListener(this);
        mMediaPlayer.setOnCompletionListener(this);
        mMediaPlayer.setOnPreparedListener(this);
        mMediaPlayer.setOnVideoSizeChangedListener(this);
        mMediaPlayer.setPlaybackSpeed(speed);
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        Log.d(TAG, "onBufferingUpdate " + i);
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        Log.d(TAG, "onCompletion");
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
        }
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        Log.d(TAG, "onPrepared");
        mMediaPlayer.start();
    }

    @Override
    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i1) {
        Log.d(TAG, "onVideoSizeChanged  " + i + "      " + i1);
    }

    @OnClick({R.id.btn_speed_decrease, R.id.btn_speed_increase})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_speed_decrease:
                if (speed >= 0.1f) {
                    speed -= 0.1f;
                    mMediaPlayer.setPlaybackSpeed(speed);
                    SimpleToast("speed=" + speed);
                } else {
                    SimpleToast("speed=" + speed);
                }
                break;
            case R.id.btn_speed_increase:
                if (speed <= 1.9f) {
                    speed += 0.1f;
                    mMediaPlayer.setPlaybackSpeed(speed);
                    SimpleToast("speed=" + speed);
                } else {
                    SimpleToast("speed=" + speed);
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMediaPlayer.release();
    }
}
