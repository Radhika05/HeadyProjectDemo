package com.radhika.headyapp.model;

import androidx.room.Embedded;

public class TempProductDetails {

    @Embedded(prefix = "_product_")
    public Products products;

    @Embedded
    public Variants variants;

    @Embedded(prefix = "tax_")
    public Tax tax;

    public Variants getVariants() {
        return variants;
    }

    public void setVariants(Variants variants) {
        this.variants = variants;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }
}
