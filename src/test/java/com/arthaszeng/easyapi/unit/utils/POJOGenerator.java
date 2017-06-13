package com.arthaszeng.easyapi.unit.utils;

import com.arthaszeng.easyapi.entity.Category;
import com.arthaszeng.easyapi.entity.Product;
import com.arthaszeng.easyapi.entity.Source;

public class POJOGenerator {
    public static final long VALID_CATEGORY_ID = 1L;
    public static final long INVALID_CATEGORY_ID = -1L;
    public static final long VALID_PRODUCT_ID = 1L;
    public static final long INVALID_PRODUCT_ID = -1L;
    public static final long VALID_SOURCE_ID = 1L;
    public static final long INVALID_SOURCE_ID = -1L;
    public static final String PRODUCT_GROUP = "TEST";
    public static final String CODE = "1234";
    public static final String DESCRIPTION = "TEST";
    public static final String DETAILED_DESCRIPTION = "TEST";


    public static Category validCategory() {
        return new Category(DESCRIPTION, DETAILED_DESCRIPTION);
    }

    public static Category invalideCategoryWithEmpty() {
        return new Category("", "");
    }

    public static Category invalideCategoryWithNullField() {
        return new Category(null, null);
    }

    public static Source invalidSource() {
        return new Source(null, "");
    }

    public static Source valideSource() {
        return new Source(CODE, DESCRIPTION);
    }

    public static Product invalidProductWithInvalidSource() {
        return new Product(PRODUCT_GROUP, new Category("", ""), null);
    }

    public static Product invalidProductWithInvalidProductGroup() {
        return new Product("", new Category("", ""), new Source("", ""));
    }

    public static Product invalidProductWithInvalidCategory() {
        return new Product(PRODUCT_GROUP, null, new Source("", ""));
    }
}
