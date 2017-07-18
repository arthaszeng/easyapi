package com.arthaszeng.easyapi.model;

import java.util.Date;

public class MovieData {
    private String score;
    private String picUrl;
    private Date createdAt;
    private String name;
    private boolean isRender;
    private String objectId;
    private int zIndex;
    private Date updatedAt;

    public MovieData() {
    }

    public MovieData(String score, String picUrl, Date createdAt, String name, boolean isRender, String objectId, int zIndex, Date updatedAt) {
        this.score = score;
        this.picUrl = picUrl;
        this.createdAt = createdAt;
        this.name = name;
        this.isRender = isRender;
        this.objectId = objectId;
        this.zIndex = zIndex;
        this.updatedAt = updatedAt;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRender() {
        return isRender;
    }

    public void setRender(boolean render) {
        isRender = render;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public int getzIndex() {
        return zIndex;
    }

    public void setzIndex(int zIndex) {
        this.zIndex = zIndex;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
