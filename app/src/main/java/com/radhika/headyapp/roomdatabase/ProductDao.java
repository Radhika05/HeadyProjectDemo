package com.radhika.headyapp.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.radhika.headyapp.model.Categories;
import com.radhika.headyapp.model.Child_Category;
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

    @Insert
    void insertSubCategory(List<Child_Category> child_categories);

    @Query("SELECT * FROM categories where categories.id in (select child_category.catId from child_category)")
    public List<Categories> getCategories();

    @Query("SELECT * FROM categories where categories.id in (select child_category.catId from child_category)")
    public LiveData<List<Categories>> getCategoriesdata();


    @Query("SELECT * FROM products left join categories on products.category = categories.id where products.category=:id")
    public LiveData<List<Products>> getSubCategoriesdata(int id);

    @Query("SELECT * FROM products left join categories on products.category = categories.id where products.id=:id")
    public List<Products> getSubCategoriesdataA(int id);
}
