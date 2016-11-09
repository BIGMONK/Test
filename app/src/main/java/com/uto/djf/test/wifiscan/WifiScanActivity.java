package com.uto.djf.test.wifiscan;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.uto.djf.test.activity.BaseActivity;
import com.uto.djf.test.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;

public class WifiScanActivity extends BaseActivity {


    @BindView(R.id.btn_scan_wifi)
    Button scan;
    @BindView(R.id.btn_sort_list)
    Button sort;
     @BindView(R.id.listView1)
    ListView listView1;
    public ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
    public StartScan scanObject;
    public SimpleAdapter listItemAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wifi_scan;
    }

    @Override
    protected void initView() {
        String connectivity_context = Context.WIFI_SERVICE;
        WifiManager wifi = (WifiManager) getSystemService(connectivity_context);
        scanObject = new StartScan(wifi);


        listItemAdapter = new SimpleAdapter(this, listItem,
                R.layout.vlist,

                new String[]{"ItemSSID", "ItemBSSID", "ItemRSI"},

                new int[]{R.id.SSID, R.id.BSSID, R.id.RSI});


        listView1.setAdapter(listItemAdapter);

        scan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Handler handler = new Handler();
                handler.post(run);
            }
        });


        sort.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                List<ScanResult> result = scanObject.sortScanResultWithRSS();
                addListToAdapter(result);
            }

        });

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    Runnable run = new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            List<ScanResult> result = scanObject.scan();
            addListToAdapter(result);
        }
    };

    void addListToAdapter(List<ScanResult> result) {

        listItem.clear();

        Iterator<ScanResult> iscan = result.iterator();
        while (iscan.hasNext()) {
            ScanResult next = iscan.next();
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemSSID", next.SSID);//
            map.put("ItemBSSID", next.BSSID);
            map.put("ItemRSI", next.level);
            listItem.add(map);
        }
        listItemAdapter.notifyDataSetChanged();
    }
}
