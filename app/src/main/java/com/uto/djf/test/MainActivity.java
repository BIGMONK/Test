package com.uto.djf.test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uto.djf.test.bean.RoadLineBean;
import com.uto.djf.test.recyclerview.RecyclerViewActivity;
import com.uto.djf.test.screenmesure.ScreenMesureActivity;
import com.uto.djf.test.utils.FileUtils;
import com.uto.djf.test.utils.JsonTool;

import java.io.File;
import java.util.List;

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


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        // tv.setText(FileUtils.getSdCardPath());
        File file = new File(FileUtils.getSdCardPath() +File.separator+"vr"+File.separator+"MultiPersonModel.json");
        Gson  gson=new  Gson();
        List<RoadLineBean> objects= JsonTool.getObjects(FileUtils.getStringFromJsonFile(file), new TypeToken<List<RoadLineBean>>() {}.getType());
        textview.setText(objects.get(0).getPageHeader().getName());






        Typeface fontFace = Typeface.createFromAsset(getAssets(), "fonts/xiyuan.ttf");
        textview2.setTypeface(fontFace);
        textview2.setText("细圆字体");

        Typeface fontFace2 = Typeface.createFromAsset(getAssets(), "fonts/maozedong.ttf");
        textview3.setTypeface(fontFace2);
        textview3.setText("毛泽东字体");
        editText.setTypeface(fontFace2);

        Typeface fontFace3 = Typeface.createFromAsset(getAssets(), "fonts/youjiyetaikaishu.ttf");
        textview4.setTypeface(fontFace3);
        textview4.setText("由纪叶太楷书");



    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.btn_get_screen_parameter, R.id.btn_expend_listview,
            R.id.btn_recycler, R.id.btn_image_identify, R.id.hide_keyboard,
            R.id.btn_draw_line, R.id.btn_circle_imageview, R.id.btn_download_asynctask
    })
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_download_asynctask:
                gotoActivity(NetworkActivity.class);
                break;
            case R.id.btn_circle_imageview:
                gotoActivity(CircleImageViewActivity.class);
                break;
            case R.id.btn_draw_line:
                gotoActivity(DrawLineActivity.class);
                break;

            case R.id.btn_get_screen_parameter:
                startActivity(new Intent(this, ScreenMesureActivity.class));
                break;
            case R.id.btn_expend_listview:
                startActivity(new Intent(this, ExpandableListViewActivity.class));
                break;

            case R.id.btn_recycler:
                startActivity(new Intent(this, RecyclerViewActivity.class));
                break;
            case R.id.btn_image_identify:
                startActivity(new Intent(this, ImageIdentifyActivity.class));
                break;
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
