package com.arthaszeng.easyapi.model;

public class Face {
    private String beauty;
    private String age;

    public Face(String beauty, String age) {
        this.beauty = beauty;
        this.age = age;
    }

    public String getBeauty() {
        return beauty;
    }

    public void setBeauty(String beauty) {
        this.beauty = beauty;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
