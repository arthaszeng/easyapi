package com.arthaszeng.easyapi.model;

public class Age {
//    {"outputs":[{"outputLabel":"feature","outputValue":{"dataType":50,"dataValue":"{\"age\":[33],\"errno\":0,\"number\":1,\"rect\":[593,88,191,191]}\n"},"outputMulti":{}}]}
    private String age;

    public Age(String age) {
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
