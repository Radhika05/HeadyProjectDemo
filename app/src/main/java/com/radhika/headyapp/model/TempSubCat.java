package com.radhika.headyapp.model;


import androidx.room.Embedded;



public class TempSubCat {

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public Child_Category getChild_category() {
        return child_category;
    }

    public void setChild_category(Child_Category child_category) {
        this.child_category = child_category;
    }

    @Embedded
    public Categories categories;

    @Embedded(prefix = "subcat")
    public Child_Category child_category;
}
