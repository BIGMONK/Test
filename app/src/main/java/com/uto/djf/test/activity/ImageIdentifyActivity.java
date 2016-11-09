package com.uto.djf.test.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hanuor.onyx.Onyx;
import com.hanuor.onyx.hub.OnTaskCompletion;
import com.uto.djf.test.R;
import com.uto.djf.test.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImageIdentifyActivity extends AppCompatActivity implements OnTaskCompletion {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tv_result)
    TextView tvResult;
    private String url;

    String[] urls = new String[]{
            "http://img1b.xgo-img.com.cn/pics/1485/1484507.jpg",
            "http://picm.photophoto.cn/005/008/007/0080071498.jpg",
            "http://www.pptbz.com/pptpic/UploadFiles_6909/201204/2012041411433867.jpg",
            "http://img0.imgtn.bdimg.com/it/u=3694817734,2254288824&fm=21&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=2095131399,1090624338&fm=21&gp=0.jpg",
            "http://pic7.nipic.com/20100524/2132325_084256961948_2.jpg",
            "http://img5.imgtn.bdimg.com/it/u=3041965182,2803854327&fm=21&gp=0.jpg",
            "http://pic21.nipic.com/20120507/4551049_174910064001_2.jpg",
            "http://img2.imgtn.bdimg.com/it/u=2849324339,1438387335&fm=21&gp=0.jpg"
    };
    private int index;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_identify);
        ButterKnife.bind(this);
        index = new Random().nextInt(Math.round(urls.length));
        url = urls[index];
        Glide.with(this).load(url).into(imageView);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在获取识别结果");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        if (progressDialog != null && !progressDialog.isShowing())
            progressDialog.show();

        Onyx.with(this).fromURL(url).getTagsandProbability(this);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 100:
                    if (progressDialog != null && !progressDialog.isShowing())
                        progressDialog.show();
                    break;
            }
        }
    };

    @OnClick(R.id.imageView)
    public void onClick() {

        tvResult.setText("正在获取结果");
        handler.sendEmptyMessage(100);
        index = new Random().nextInt(Math.round(urls.length));
        System.out.println("请求识别" + index);

        url = urls[index];
        Glide.with(this).load(url).into(imageView);

        Onyx.with(this).fromURL(url).getTagsandProbability(this);
    }

    @Override
    public void onComplete(ArrayList<String> response) {

        //results will be in the form of array-list
        //for eg. - [Mammal-0.9972132, Wolf-0.9962321, Snow-0.993212]
        //Do whatever you want to do here
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
            }
        });

        StringBuilder sb = new StringBuilder();
        for (String s : response) {
            sb.append(s);
        }
        System.out.println("返回结果:" + response.toString());
//        ToastUtils.SimpleToast(ImageIdentifyActivity.this, sb.toString());
        tvResult.setText(response.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        progressDialog.dismiss();
    }
}
