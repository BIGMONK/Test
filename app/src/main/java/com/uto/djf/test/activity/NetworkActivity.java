package com.uto.djf.test.activity;

import android.app.ProgressDialog;
import android.os.Environment;
import android.widget.TextView;

import com.uto.djf.test.R;
import com.uto.djf.test.utils.DownloadAsyncTask;

import butterknife.BindView;

public class NetworkActivity extends BaseActivity {


    private static final String NATIVE_SAVE_PATH = Environment.getExternalStorageDirectory() + "/00/";
    private static final String UNZIP_SAVE_PATH = Environment.getExternalStorageDirectory() + "/01/";
    @BindView(R.id.tv_show)
    TextView tvShow;
        String url = "http://192.168.1.37:8080/test/test.zip";
//        String url = "http://172.20.85.1:8080/test/test.zip";
//    String url = "http://192.168.31.177:80/test/test.zip";
    String[] params = new String[]{url, NATIVE_SAVE_PATH, "dao.zip", UNZIP_SAVE_PATH};
    private ProgressDialog perDialog;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_network;
    }

    @Override
    protected void initView() {

        DownloadAsyncTask downloadAsyncTask = new DownloadAsyncTask(this);
        downloadAsyncTask.setOnUnzipFileListener(new DownloadAsyncTask.UnzipFileListener() {
            @Override
            public void onUnzipFileListener(boolean isOver) {
                if (isOver) {
                    perDialog.dismiss();
                    SimpleToast("解压完成");
                } else {

                    perDialog = new ProgressDialog(NetworkActivity.this);
                    perDialog.setTitle("解压中...");
                    perDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    perDialog.setCancelable(false);
                    perDialog.show();
                }
            }
        });
        downloadAsyncTask.setOnDownloadListener(new DownloadAsyncTask.DownloadListener() {
            @Override
            public void onDownloadListener(boolean succed) {
                if (succed){
                    SimpleToast("下载成功");
                }else{
                    SimpleToast("下载失败！");
                }
            }
        });
        downloadAsyncTask.execute(params);

    }



    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }


}
