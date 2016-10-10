package com.uto.djf.test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.uto.djf.test.recyclerview.RecyclerViewActivity;
import com.uto.djf.test.screenmesure.ScreenMesureActivity;
import com.uto.djf.test.wifiscan.WifiScanActivity;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {


    @BindView(R.id.btn_draw_line)
    Button btnDrawLine;
    @BindView(R.id.btn_get_screen_parameter)
    Button btnGetScreenParameter;
    @BindView(R.id.btn_expend_listview)
    Button btnExpendListview;
    @BindView(R.id.btn_recycler)
    Button btnRecycler;
    @BindView(R.id.btn_image_identify)
    Button btnImageIdentify;
    @BindView(R.id.textview)
    TextView textview;
    @BindView(R.id.textview2)
    TextView textview2;
    @BindView(R.id.textview3)
    TextView textview3;
    @BindView(R.id.textview4)
    TextView textview4;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.hide_keyboard)
    Button hideKeyboard;
    @BindView(R.id.btn_circle_imageview)
    Button btnCircleImageview;
    @BindView(R.id.btn_download_asynctask)
    Button btnDownloadAsynctask;
    @BindView(R.id.iv_main)
    ImageView ivMain;
    @BindView(R.id.iv_main2)
    ImageView ivMain2;
    @BindView(R.id.btn_getSDCardPath)
    Button btnGetSDCardPath;
    @BindView(R.id.btn_wifi_scan)
    Button btnWifiScan;
    @BindView(R.id.btn_video_play)
    Button btnVideoPlay;
    @BindView(R.id.btn_activity_start)
    Button btnActivityStart;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
//背景加载
//        ivMain.setBackground(Drawable.createFromPath(new File(Environment.getExternalStorageDirectory(),
//                "/00/1.jpg").getAbsolutePath()));//可以
//        ivMain.setBackground(Drawable.createFromPath(new File(Environment.getExternalStorageDirectory()+"/00/",
//                "1.jpg").getAbsolutePath()));//可以
        //图片加载
//        Glide.with(this).load(Environment.getExternalStorageDirectory()+"/00/1.jpg").into(ivMain2);//可以
//        Glide.with(this).load("/mnt/sdcard/00/1.jpg").into(ivMain2);//可以
        // tv.setText(FileUtils.getSdCardPath());
//        File file = new File(FileUtils.getSdCardPath() +File.separator+"vr"+File.separator+"MultiPersonModel.json");
//        Gson  gson=new  Gson();
//        List<RoadLineBean> objects= JsonTool.getObjects(FileUtils.getStringFromJsonFile(file), new TypeToken<List<RoadLineBean>>() {}.getType());
//        textview.setText(objects.get(0).getPageHeader().getName());

//
//        Typeface fontFace = Typeface.createFromAsset(getAssets(), "fonts/xiyuan.ttf");
//        textview2.setTypeface(fontFace);
//        textview2.setText("细圆字体");
//
//        Typeface fontFace2 = Typeface.createFromAsset(getAssets(), "fonts/maozedong.ttf");
//        textview3.setTypeface(fontFace2);
//        textview3.setText("毛泽东字体");
//        editText.setTypeface(fontFace2);
//
//        Typeface fontFace3 = Typeface.createFromAsset(getAssets(), "fonts/youjiyetaikaishu.ttf");
//        textview4.setTypeface(fontFace3);
//        textview4.setText("由纪叶太楷书");


    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.btn_get_screen_parameter, R.id.btn_expend_listview,
            R.id.btn_recycler, R.id.btn_image_identify, R.id.hide_keyboard,
            R.id.btn_draw_line, R.id.btn_circle_imageview, R.id.btn_download_asynctask,
            R.id.btn_getSDCardPath, R.id.btn_wifi_scan, R.id.btn_video_play,
            R.id.btn_activity_start, R.id.btn_fori_time_cost_test, R.id.btn_net_resource_test,
            R.id.btn_thread_pool_test,R.id.btn_ip_get_test

    })
    public void onClick(View view) {
        switch (view.getId()) {

            //ip获取测试
            case R.id.btn_ip_get_test:
                gotoActivity(IPGetActivity.class);
                break;
            //线程池测试
            case R.id.btn_thread_pool_test:
                gotoActivity(ThreadPoolTestActivity.class);
                break;
            //网络资源测试
            case R.id.btn_net_resource_test:
                gotoActivity(NetResourceTestActivity.class);
                break;
            //耗时遍历测试
            case R.id.btn_fori_time_cost_test:
                gotoActivity(ForiTimeTestActivity.class);
                break;
            //activity
            case R.id.btn_activity_start:
                gotoActivity(AActivity.class);
                break;

            //视频播放
            case R.id.btn_video_play:
                gotoActivity(VideoPlayActivity.class);
                break;

            //获取wifi列表
            case R.id.btn_wifi_scan:
                gotoActivity(WifiScanActivity.class);
                break;

            //获取存储卡路径
            case R.id.btn_getSDCardPath:
                gotoActivity(SDCardPathActivity.class);
                break;
            //异步下载与解压
            case R.id.btn_download_asynctask:
                gotoActivity(NetworkActivity.class);
                break;
            //圆形imageview
            case R.id.btn_circle_imageview:
                gotoActivity(CustomViewActivity.class);
                break;
            //
            case R.id.btn_draw_line:
                gotoActivity(DrawLineActivity.class);
                break;
            //获取屏幕参数
            case R.id.btn_get_screen_parameter:
                startActivity(new Intent(this, ScreenMesureActivity.class));
                break;
            //ExpendableListView
            case R.id.btn_expend_listview:
                startActivity(new Intent(this, ExpandableListViewActivity.class));
                break;
            //RecyvlerView
            case R.id.btn_recycler:
                startActivity(new Intent(this, RecyclerViewActivity.class));
                break;
            //图片识别
            case R.id.btn_image_identify:
                startActivity(new Intent(this, ImageIdentifyActivity.class));
                break;
            //隐藏系统键盘
            case R.id.hide_keyboard:
                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                break;
        }

    }


    private void gotoActivity(Class cal) {
        startActivity(new Intent(this, cal));
    }

}
