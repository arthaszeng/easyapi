package com.arthaszeng.easyapi.entity;

import javax.persistence.*;


@Entity
@Table(name = "source")
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sourceId;

    private String code;

    private String description;

    public Source() {
    }

    public Source(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public Long getsourceId() {
        return sourceId;
    }

    public void setsourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
