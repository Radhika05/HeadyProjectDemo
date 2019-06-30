package com.radhika.headyapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
@ForeignKey(entity = Categories.class, parentColumns = "id", childColumns = "category")
public class Products {

    @ColumnInfo(name = "category")
    private int category_id;

    @PrimaryKey
    @ColumnInfo
    @SerializedName("id")
    private int id;

    @ColumnInfo
    @SerializedName("name")
    private String name;

    @ColumnInfo
    @SerializedName("date_added")
    private String date_product;

    @Ignore
    @SerializedName("variants")
    private List<Variants> variants;

    @Ignore
    @SerializedName("tax")
    private Tax tax;


    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public List<Variants> getVariants() {
        return variants;
    }

    public void setVariants(List<Variants> variants) {
        this.variants = variants;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate_product() {
        return date_product;
    }

    public void setDate_product(String date_product) {
        this.date_product = date_product;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

}
