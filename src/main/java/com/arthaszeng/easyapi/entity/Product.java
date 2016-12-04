// tag::sample[]
package com.arthaszeng.easyapi.entity;


import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "product_group")
    private String product_group;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne()
    @JoinColumn(name = "source_id")
    private Source source;

    protected Product() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_group() {
        return product_group;
    }

    public void setProduct_group(String product_group) {
        this.product_group = product_group;
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
//@Entity()
//@Table(name = "product")
//public class Product {
//
//    @Id
//    @GeneratedValue(strategy=GenerationType.AUTO)
//    private String id;
//
//    private String product_group;
//
//    private String sourceId;
//
//    private String categoryId;
//
//    protected Product() {}
//
//    public Product(String product_group, String sourceId, String categoryId) {
//        this.product_group = product_group;
//        this.sourceId = sourceId;
//        this.categoryId = categoryId;
//    }
//
//    @Override
//    public String toString() {
//        return String.format(
//                "Product[id='%s', product_group='%s', sourceId='%s', categoryId='%s']",
//                id, product_group, sourceId, categoryId);
//    }
//
//    public String getCategoryId() {
//        return categoryId;
//    }
//
//    public String getId() {
//		return id;
//	}
//
//	public String getProduct_group() {
//		return product_group;
//	}
//
//	public String getSourceId() {
//		return sourceId;
//	}
//}

