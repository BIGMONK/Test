package com.uto.djf.test;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.uto.djf.test.view.MyVideoView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoPlayActivity extends BaseActivity {
    @BindView(R.id.videoview)
    MyVideoView videoview;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_play;
    }

    @Override
    protected void initView() {
//        videoview.setVideoPath("/mnt/sdcard/v.mp4");
        videoview.setVideoPath("/mnt/usb_storage/USB_DISK2/hong/km1930/resources/single/5000/playvideo5000.mp4");
        final MediaController mediaController=new MediaController(this);

        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoview.setMediaController(mediaController);
                mediaController.setMediaPlayer(videoview);
                videoview.start();
                videoview.requestFocus();
            }
        });

        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoview.start();
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

}
