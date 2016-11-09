package com.uto.djf.test.activity;

import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;

import com.uto.djf.test.R;
import com.uto.djf.test.view.MyVideoView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import butterknife.BindView;

public class VideoViewPlayActivity extends BaseActivity {
    @BindView(R.id.videoview)
    MyVideoView videoview;
    @BindView(R.id.progress_bar_copying)
    ProgressBar progressBar;

    final static int COPYSTART = 1;
    final static int COPYOVER = 2;
    private String TAG = this.getClass().getSimpleName();
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case COPYSTART:
                    System.out.println(TAG + "    " + 3);
                    progressBar.setVisibility(View.VISIBLE);
                    break;
                case COPYOVER:
                    progressBar.setVisibility(View.GONE);
                    System.out.println(TAG + "    " + 4);
                    setVideo(copyDir + File.separator + copyFileName);
                    break;
            }
        }
    };
    private String copyDir;
    private String copyFileName;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_play;
    }

    @Override
    protected void initView() {


        String originFileName = "xunhuan.mp4";
        copyDir = "/mnt/sdcard/xunhuan";
        copyFileName = "beijing.mp4";

        copyToLocal(originFileName, copyDir, copyFileName);

    }

    /**
     * assets复制到指定文件夹
     *
     * @param originFileName
     * @param copyDir
     * @param copyFileName
     */
    private void copyToLocal(final String originFileName, final String copyDir, final String copyFileName) {
        // 拿到资产目录的管理者
        new Thread() {
            public void run() {
                System.out.println(TAG + "    " + 1);
                // 判断一下之前是否已经有文件
//                File file = new File(getFilesDir(), dbName);
                File file = new File(copyDir, copyFileName);
                if (file.exists() && file.length() > 0) {
                    System.out.println(TAG + "    " + 2);
                    handler.sendEmptyMessage(COPYOVER);
                    return;
                }

                handler.sendEmptyMessage(COPYSTART);
                AssetManager assets = getAssets();
                try {
                    InputStream inputStream = assets.open(originFileName);
                    // 定义输出流 /data/data/包名/files/dbName
//                    FileOutputStream fileOutput = openFileOutput(dbName, MODE_PRIVATE);

                    File destDir = new File(copyDir);
                    if (!destDir.exists()) {
                        destDir.mkdirs();
                    }
                    FileOutputStream fileOutput = new FileOutputStream(file);
                    int len = -1;
                    byte[] buf = new byte[1024];
                    while ((len = inputStream.read(buf)) != -1) {
                        fileOutput.write(buf, 0, len);
                        System.out.println(TAG + "    " + "3..");
                    }

                    inputStream.close();
                    fileOutput.close();
                    handler.sendEmptyMessage(COPYOVER);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }


    private void setVideo(String filePath) {
        //        String uri = "android.resource://" + getPackageName() + "/" + R.raw.xunhuan;
//        videoview.setVideoURI(Uri.parse(uri));

//        videoview.setVideoPath(getFilesDir() + "/xunhuan.mp4");
//
//        afd = getAssets().openFd("xunhuan.mp4");
//        System.out.println("sadfsaddddddd==" + afd.getFileDescriptor());


//        videoview.setVideoPath(filePath);
        videoview.setVideoPath("http://192.168.1.37:8080/km1930/resources/single/1000/playvideo1000c.mp4");
//        videoview.setVideoPath("http://192.168.1.37:8080/km1930/resources/single/9000/playvideo9000.mp4");
//        videoview.setVideoPath("http://km1930.oss-cn-shanghai.aliyuncs.com/Video/playvideo9000.mp4");
//        videoview.setVideoPath("http://km1930.oss-cn-shanghai.aliyuncs.com/Video/playvideo5000.mp4");

        final MediaController mediaController = new MediaController(this);

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
                videoview.stopPlayback();
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
