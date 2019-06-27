package com.radhika.headyapp.roomdatabase;

import androidx.room.Dao;
import androidx.room.Insert;

import com.radhika.headyapp.model.Categories;
import com.radhika.headyapp.model.Products;
import com.radhika.headyapp.model.RankProduct;
import com.radhika.headyapp.model.Rankings;
import com.radhika.headyapp.model.Tax;
import com.radhika.headyapp.model.Variants;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert
    void insertCategory(List<Categories> categories);

    @Insert
    void insertRanking(List<Rankings> rankings);

    @Insert
    void insertProduct(List<Products> products);

    @Insert
    void insertVariant(List<Variants> variants);

    @Insert
    void insertTaxes(List<Tax> variants);

    @Insert
    void insertProductRanking(List<RankProduct> rankings);

}
