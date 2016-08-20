package com.uto.djf.test.bean;

import java.util.List;

/**
 * Created by djf on 2016/8/20.
 */

public class RoadLineBean {

    /**
     * name : 多人线路1
     */

    private PageHeaderBean pageHeader;
    /**
     * title : 川藏线1
     * distance : 200km
     * participation : 134人
     * suggestTime : Suggest time 15h
     * englishName : Sichuan-Tibet
     * actionMeshUrl : bin/mesh/screen.objx
     * actionSourceUrl : /mnt/sdcard/Movies/exhibition_demo_01.mp4
     * slopeSourceUrl : prefabs/data/slopeInfo/SlopeMessage1.json
     * sealevelSourceUrl : prefabs/data/seaLevelInfo/SeaLevelMessage1.json
     * imageUrl : http://youtuvr.oss-cn-shanghai.aliyuncs.com/images/a00.jpg
     * describeImageUrl : prefabs/images/singlePerson/single_person_map_cuanzang.png
     * describeMessage : hello world
     * branchMessageUrl : prefabs/data/branchMessage/chuanzangxianbranch.json
     * gifUrl : http://youtuvr.oss-cn-shanghai.aliyuncs.com/gif/gif003.gif
     */

    private List<PageContentBean> pageContent;

    public PageHeaderBean getPageHeader() {
        return pageHeader;
    }

    public void setPageHeader(PageHeaderBean pageHeader) {
        this.pageHeader = pageHeader;
    }

    public List<PageContentBean> getPageContent() {
        return pageContent;
    }

    public void setPageContent(List<PageContentBean> pageContent) {
        this.pageContent = pageContent;
    }

    public static class PageHeaderBean {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class PageContentBean {
        private String title;
        private String distance;
        private String participation;
        private String suggestTime;
        private String englishName;
        private String actionMeshUrl;
        private String actionSourceUrl;
        private String slopeSourceUrl;
        private String sealevelSourceUrl;
        private String imageUrl;
        private String describeImageUrl;
        private String describeMessage;
        private String branchMessageUrl;
        private String gifUrl;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getParticipation() {
            return participation;
        }

        public void setParticipation(String participation) {
            this.participation = participation;
        }

        public String getSuggestTime() {
            return suggestTime;
        }

        public void setSuggestTime(String suggestTime) {
            this.suggestTime = suggestTime;
        }

        public String getEnglishName() {
            return englishName;
        }

        public void setEnglishName(String englishName) {
            this.englishName = englishName;
        }

        public String getActionMeshUrl() {
            return actionMeshUrl;
        }

        public void setActionMeshUrl(String actionMeshUrl) {
            this.actionMeshUrl = actionMeshUrl;
        }

        public String getActionSourceUrl() {
            return actionSourceUrl;
        }

        public void setActionSourceUrl(String actionSourceUrl) {
            this.actionSourceUrl = actionSourceUrl;
        }

        public String getSlopeSourceUrl() {
            return slopeSourceUrl;
        }

        public void setSlopeSourceUrl(String slopeSourceUrl) {
            this.slopeSourceUrl = slopeSourceUrl;
        }

        public String getSealevelSourceUrl() {
            return sealevelSourceUrl;
        }

        public void setSealevelSourceUrl(String sealevelSourceUrl) {
            this.sealevelSourceUrl = sealevelSourceUrl;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getDescribeImageUrl() {
            return describeImageUrl;
        }

        public void setDescribeImageUrl(String describeImageUrl) {
            this.describeImageUrl = describeImageUrl;
        }

        public String getDescribeMessage() {
            return describeMessage;
        }

        public void setDescribeMessage(String describeMessage) {
            this.describeMessage = describeMessage;
        }

        public String getBranchMessageUrl() {
            return branchMessageUrl;
        }

        public void setBranchMessageUrl(String branchMessageUrl) {
            this.branchMessageUrl = branchMessageUrl;
        }

        public String getGifUrl() {
            return gifUrl;
        }

        public void setGifUrl(String gifUrl) {
            this.gifUrl = gifUrl;
        }
    }
}
