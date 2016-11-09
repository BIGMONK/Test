package com.uto.djf.test.activity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;

import com.ut.vrmedia.VRPlayer;
import com.ut.vrmedia.camera.VROrthogonalCamera;
import com.ut.vrmedia.view.VRSurfaceView;
import com.uto.djf.test.R;
import com.uto.djf.test.database.DatabaseManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.dao.query.Query;
import greendao.RoadTime;
import greendao.RoadTimeDao;

public class VRVideoActivity extends BaseActivity implements SurfaceHolder.Callback, VRPlayer.OnPreparedListener, VRPlayer.OnCompletionListener {

    @BindView(R.id.player_vrsurface_view)
    VRSurfaceView mPlayerVrsurfaceView;
    private String mActionSourceUrl;
    private Map<String, String> mParams;
    private VRPlayer mVRPlayer;
    private VROrthogonalCamera mCamera;
    private RoadTimeDao mRoadTimeDao;
    private Integer mPlayTime;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_vrvideo;
    }

    public static final String PARAM_TITIE = "title";
    public static final String PARAM_MESH_URL = "MeshUrl";
    public static final String PARAM_RESOURCE_URL = "ResourceUrl";
    public static final String PARAM_PLAYER_MODE = "playerMode";

    @Override
    protected void initView() {
        mActionSourceUrl = "http://192.168.1.37:8080/km1930/resources/single/1000/playvideo1000c.mp4";
        mParams = new HashMap<>();
        mParams.put(PARAM_TITIE, "test");
        mParams.put(PARAM_MESH_URL, "test");
        mParams.put(PARAM_RESOURCE_URL, mActionSourceUrl);
        mParams.put(PARAM_PLAYER_MODE, "test");
        mVRPlayer = new VRPlayer(this);

        mRoadTimeDao = DatabaseManager.getInstance().mDaoSession.getRoadTimeDao();

        mPlayerVrsurfaceView.getHolder().addCallback(this);
        mVRPlayer.setOnPreparedListener(this);
        mVRPlayer.setOnCompletionListener(this);
//        mVRPlayer.setOnErrorListener(this);


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
            mVRPlayer.setDataSource((String) mParams.get(PARAM_RESOURCE_URL), null);
            mVRPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void onPrepared(VRPlayer vrPlayer) {
        InitPlayerAndCamera();

        mVRPlayer.start();


    }

    //初始相机参数
    private void InitPlayerAndCamera() {
        Map<String, String> params = new HashMap<>();
        params.put(VRPlayer.CURVED_SURFACE_WIDTH, "1920.0");
//        params.put(VRPlayer.CURVED_SURFACE_EXPONENT, "0.1");
        params.put(VRPlayer.CURVED_SURFACE_HEIGHT, "1080");
        params.put(VRPlayer.CURVED_SURFACE_WIDTH_SEGS, "12");
        params.put(VRPlayer.CURVED_SURFACE_HEIGHT_SEGS, "1");

        mVRPlayer.setVideoRenderMode(VRPlayer.VIDEO_RENDER_MODE_ORIGIN, params);
        mVRPlayer.setRenderTargetPosition(new float[]{0.0f, -100.0f, -800.0f});
        mCamera = new VROrthogonalCamera(-mPlayerVrsurfaceView.getWidth() / 2, mPlayerVrsurfaceView.getWidth() / 2,
                -mPlayerVrsurfaceView.getHeight() / 2, mPlayerVrsurfaceView.getHeight() / 2, 0.5f, 1500f);
        mVRPlayer.setCamera(mCamera);
        mVRPlayer.setSurface(mPlayerVrsurfaceView.getHolder().getSurface());

        Query query = mRoadTimeDao.queryBuilder()
                .where(RoadTimeDao.Properties.Road_name.eq(mParams.get(PARAM_TITIE).toString()))
                .build();
        List<RoadTime> roadTime = query.list();

        if (roadTime != null && roadTime.size() > 0) {
            mPlayTime = roadTime.get(0).getStop_time();
//            mVRPlayer.seekTo(mPlayTime);
        }

    }

    @Override
    public void onCompletion(VRPlayer vrPlayer) {
        if (mVRPlayer != null) {
            stopPlayer();
        }
    }

    //停止播放视频
    public void stopPlayer() {
        InsertStopTime();
        if (mVRPlayer != null) {
            mCamera = null;
            mVRPlayer.stop();
            mVRPlayer.reset();
            mVRPlayer.release();
            mVRPlayer = null;
        }

    }

    //在数据库里面插入断点时间
    private void InsertStopTime() {
        RoadTime roadTime = new RoadTime();
        roadTime.setRoad_name(mParams.get(PARAM_TITIE).toString());
        roadTime.setStop_time(mVRPlayer.getCurrentPosition());
        mRoadTimeDao.insertOrReplace(roadTime);
    }
}
