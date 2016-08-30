package com.uto.djf.test;

import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.widget.TextView;

import com.uto.djf.test.utils.DownloadAsyncTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NetworkActivity extends BaseActivity {


    @BindView(R.id.tv_show)
    TextView tvShow;

    private static final String NATIVE_SAVE_PATH = Environment.getExternalStorageDirectory() + "/00/";
    private static final String UNZIP_SAVE_PATH = Environment.getExternalStorageDirectory() + "/01/";
    //百度网盘
    //String url="http://qd.baidupcs.com/file/56d2613296d174a2216c16e68290c4ff?fid=2031568397-250528-206562463223513&time=1401850141&sign=FDTAXER-DCb740ccc5511e5e8fedcff06b081203-%2FlmpLQ%2BalXZOIdpRCS0XSJScCPE%3D&to=qb&fm=Q,B,U,nc&newver=1&expires=8h&rt=pr&r=921627517&logid=1251756051&vuk=2031568397&fn=dao.zip";
    //华为网盘
    String url = "http://192.168.3.171:8080/test/test.zip";
    String[] params = new String[]{url, NATIVE_SAVE_PATH, "dao.zip",UNZIP_SAVE_PATH};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_network;
    }

    @Override
    protected void initView() {
        new DownloadAsyncTask(this).execute(params);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }


}
