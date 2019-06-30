package com.radhika.headyapp.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.radhika.headyapp.model.Categories;
import com.radhika.headyapp.model.Child_Category;
import com.radhika.headyapp.model.Products;
import com.radhika.headyapp.model.RankProduct;
import com.radhika.headyapp.model.Rankings;
import com.radhika.headyapp.model.Tax;
import com.radhika.headyapp.model.TempProduct;
import com.radhika.headyapp.model.TempProductDetails;
import com.radhika.headyapp.model.TempSubCat;
import com.radhika.headyapp.model.Variants;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategory(List<Categories> categories);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRanking(List<Rankings> rankings);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProduct(List<Products> products);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertVariant(List<Variants> variants);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTaxes(List<Tax> variants);

    @Insert
    void insertProductRanking(List<RankProduct> rankings);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSubCategory(List<Child_Category> child_categories);

    @Query("SELECT * FROM categories where categories.id in (select child_category.catId from child_category)")
    public List<Categories> getCategories();

    @Query("select Categories.id, Categories.name from Categories\n" +
            "left join child_category on child_category.catId = Categories.id\n" +
            "where child_category.catId not in (select child_category.id from child_category)\n" +
            "group by child_category.catId")
    public LiveData<List<Categories>> getCategoriesdata();

    @Query("SELECT Categories.*, Child_Category.* FROM child_category left join categories on child_category.id = categories.id where child_category.catId=:id")
    public LiveData<List<TempSubCat>> getSubCategoriesdataA(int id);

    @Query("SELECT Products.name, Products.id FROM products left join categories on products.category = categories.id where products.category=:id")
    public LiveData<List<TempProduct>> getProduct(int id);

    @Query("select Variants.*, Tax.* from products left JOIN Variants on Variants.product_id= Products.id left JOIN Tax on Tax.product_id = Products.id WHERE Products.id=:id")
    public LiveData<List<TempProductDetails>> getProductDetails(int id);

    @Query("SELECT * from Rankings")
    public List<Rankings> getRanking();

   /* @Query("SELECT * from Rankings")
    public LiveData<List<Products>> getRankWiseProduct(int id);*/

}
