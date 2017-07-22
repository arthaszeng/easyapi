package com.arthaszeng.easyapi.model;

import java.util.List;

public class User {
    private String objectId;
    private String email;
    private List<String> images;
    private String city;
    private String nickName;
    private String avatar;
    private Integer gender;
    private String province;
    private String country;

    public User(String objectId, String email, List<String> images, String city, String nickName, String avatar, Integer gender, String province, String country) {
        this.objectId = objectId;
        this.email = email;
        this.images = images;
        this.city = city;
        this.nickName = nickName;
        this.avatar = avatar;
        this.gender = gender;
        this.province = province;
        this.country = country;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
