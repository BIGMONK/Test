package com.uto.djf.test;

import android.os.Bundle;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForiTimeTestActivity extends BaseActivity {


    @BindView(R.id.tv_time_cost)
    TextView tvTimeCost;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fori_time_test;
    }

    @Override
    protected void initView() {
        ArrayList<String> list = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 150; i++) {
            File file = new File("/mnt/sdcard/123/" + i + ".mp4");
            File file1 = new File("/mnt/sdcard/123/" + i + ".mp4");
            File file2 = new File("/mnt/sdcard/123/" + i + ".mp4");
            File file3 = new File("/mnt/sdcard/123/" + i + ".mp4");
            File file4 = new File("/mnt/sdcard/123/" + i + ".mp4");
            File file5 = new File("/mnt/sdcard/123/" + i + ".mp4");
            File file6 = new File("/mnt/sdcard/123/" + i + ".mp4");
            File file7 = new File("/mnt/sdcard/123/" + i + ".mp4");
            File file8 = new File("/mnt/sdcard/123/" + i + ".mp4");
            File file9 = new File("/mnt/sdcard/123/" + i + ".mp4");
            File file10 = new File("/mnt/sdcard/123/" + i + ".mp4");
            if (file.exists()
                    && file1.exists() && file2.exists() && file3.exists() && file4.exists() && file5.exists()
                    && file6.exists() && file7.exists() && file8.exists() && file9.exists() && file10.exists()

                    ) {

                list.add(i + "");
            }
        }
        long endTime = System.currentTimeMillis();
        tvTimeCost.setText(endTime - startTime + "cmc");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
