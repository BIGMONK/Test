package com.uto.djf.test;

import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SDCardPathActivity extends BaseActivity {

    @BindView(R.id.tv_inner_sdcard)
    TextView tvInnerSdcard;
    @BindView(R.id.tv_extral_sdcard)
    TextView tvExtralSdcard;
    @BindView(R.id.tv_usb_store)
    TextView tvUsbStore;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sdcard_path;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {
//        tvInnerSdcard.setText(Environment.getRootDirectory().toString());
//        tvExtralSdcard.setText(Environment.getExternalStorageDirectory().toString());
        //内置存储
        tvInnerSdcard.setText(getInnerSDCardPath());

        if (getExtSDCardPath().size() == 0) {
            tvExtralSdcard.setText("无外置存储卡");
        } else {
            StringBuilder sb=new StringBuilder();
            for (String path:getExtSDCardPath()){
                sb.append(path);
                sb.append("/n");
            }
            tvExtralSdcard.setText(sb);
        }

        if (getUSBStorePath().size()==0){
            tvUsbStore.setText("无外接USB存储");
        }else {
            StringBuilder sb=new StringBuilder();
            for (String path:getExtSDCardPath()){
                sb.append(path);
                sb.append("/n");
            }
            tvUsbStore.setText(sb.toString());
        }
    }

    @Override
    protected void initData() {

    }


    /**
     * 获取内置SD卡路径
     *
     * @return
     */
    public String getInnerSDCardPath() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    /**
     * 获取外置SD卡路径
     *
     * @return 应该就一条记录或空
     */
    public List<String> getExtSDCardPath() {
        List<String> lResult = new ArrayList<String>();
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec("mount");
            InputStream is = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("sdcard")) {
                    String[] arr = line.split(" ");
                    String path = arr[1];
                    File file = new File(path);
                    if (file.isDirectory()) {
                        lResult.add(path);
                    }
                }
                System.out.println("line===========" + line);
            }
            isr.close();
        } catch (Exception e) {
        }
        return lResult;
    }

    /**
     * 获取USB存储路径
     *
     * @return
     */
    public List<String> getUSBStorePath() {
        List<String> lResult = new ArrayList<String>();
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec("mount");
            InputStream is = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("USB_DISK")) {
                    String[] arr = line.split(" ");
                    String path = arr[1];
                    File file = new File(path);
                    if (file.isDirectory()) {
                        lResult.add(path);
                    }
                }
                System.out.println("line===========" + line);
            }
            isr.close();
        } catch (Exception e) {
        }
        return lResult;
    }

}
