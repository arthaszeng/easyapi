package com.arthaszeng.easyapi.entity;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "product_group")
    private String productGroup;

    @OneToOne(targetEntity=Category.class)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(targetEntity = Source.class)
    @JoinColumn(name = "source_id")
    private Source source;

    public Product() {
    }

    public Product(String productGroup, Category category, Source source) {
        this.productGroup = productGroup;
        this.category = category;
        this.source = source;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }
}

