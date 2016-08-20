package com.uto.djf.test;

import android.graphics.Point;
import android.widget.RelativeLayout;

import java.util.List;

/**
 * Created by djf on 2016/8/18.
 */
public class IconView {
    private String nickName;
    private String userId;
    private float positionPercent;
    private List<Point> pointList;
    private RelativeLayout iconView;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public float getPositionPercent() {
        return positionPercent;
    }

    public void setPositionPercent(float positionPercent) {
        this.positionPercent = positionPercent;
    }

    public List<Point> getPointList() {
        return pointList;
    }

    public void setPointList(List<Point> pointList) {
        this.pointList = pointList;
    }

    public RelativeLayout getIconView() {
        return iconView;
    }

    public void setIconView(RelativeLayout iconView) {
        this.iconView = iconView;
    }
}
