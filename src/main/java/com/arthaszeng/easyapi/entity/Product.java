// tag::sample[]
package com.arthaszeng.easyapi.entity;


import javax.persistence.*;

@Entity()
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String id;

    private String productGroup;

    @JoinColumn
    private String sourceId;


    private String categoryId;

    protected Product() {}

    public Product(String productGroup, String sourceId, String categoryId) {
        this.productGroup = productGroup;
        this.sourceId = sourceId;
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return String.format(
                "Product[id='%s', productGroup='%s', sourceId='%s', categoryId='%s']",
                id, productGroup, sourceId, categoryId);
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getId() {
		return id;
	}

	public String getProductGroup() {
		return productGroup;
	}

	public String getSourceId() {
		return sourceId;
	}
}

