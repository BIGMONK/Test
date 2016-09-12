package com.uto.djf.test.utils;

import android.os.Environment;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by djf on 2016/8/20.
 */

public class FileUtils {
    /**
     * 判断SDCard是否存在 [当没有外挂SD卡时，内置ROM也被识别为存在sd卡]
     *
     * @return
     */
    public static boolean isSdCardExist() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取SD卡根目录路径
     *
     * @return
     */
    public static String getSdCardPath() {
        boolean exist = isSdCardExist();
        String sdpath = "";
        if (exist) {
            sdpath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath();
        } else {
            sdpath = "不适用";
        }
        return sdpath;

    }

    /**
     * 判断文件是中存在
     *
     * @return
     */
    public static boolean isFileExit(String pathAndName) {
        String filepath = "";
        File file = new File(pathAndName);
        if (file.exists()) {
            return true;
        }
        return false;
    }

    public static  final Gson gson = new Gson();

    public static String getStringFromJsonFile(File file) {
        StringBuffer sb = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String readline = "";
            sb = new StringBuffer();
            while ((readline = br.readLine()) != null) {
                System.out.println("readline:" + readline);
                sb.append(readline);
            }
            br.close();
            System.out.println("读取成功：" + sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }



}
