package com.uto.djf.test;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class IPGetActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_ipget;
    }

    @Override
    protected void initView() {
        WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        String IPAddress = intToIp(wifiInfo.getIpAddress());
        System.out.println(wifiInfo.getIpAddress() + " intToIp->>" + IPAddress);
        System.out.println(IPAddress + " ipToInt->>" + ipToInt(IPAddress));

//        DhcpInfo dhcpinfo = wifiManager.getDhcpInfo();
//        String serverAddress = intToIp(dhcpinfo.serverAddress);
//        System.out.println("serverAddress-->>" + serverAddress);
    }

    /**
     * 通过左移位操作（<<）给每一段的数字加权
     * 第一段的权为2的24次方
     * 第二段的权为2的16次方
     * 第三段的权为2的8次方
     * 最后一段的权为1
     *
     * @param ip
     * @return int
     */
    public static int ipToInt(String ip) {
        String[] ips = ip.split("\\.");
        return (Integer.parseInt(ips[0]) << 0) + (Integer.parseInt(ips[1]) << 8)
                + (Integer.parseInt(ips[2]) << 16) + (Integer.parseInt(ips[3]) << 24);
    }

    /**
     * 将整数值进行右移位操作（>>）
     * 右移24位，右移时高位补0，得到的数字即为第一段IP
     * 右移16位，右移时高位补0，得到的数字即为第二段IP
     * 右移8位，右移时高位补0，得到的数字即为第三段IP
     *
     * @param i
     * @return String
     */
    public static String intToIp(int i) {
        return ((i >> 0) & 0xFF) + "." + ((i >> 8) & 0xFF) + "."
                + ((i >> 16) & 0xFF) + "." + (i >> 24 & 0xFF);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
