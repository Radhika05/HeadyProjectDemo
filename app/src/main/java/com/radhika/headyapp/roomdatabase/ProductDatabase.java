package com.radhika.headyapp.roomdatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.radhika.headyapp.model.Categories;
import com.radhika.headyapp.model.Child_Category;
import com.radhika.headyapp.model.Products;
import com.radhika.headyapp.model.RankProduct;
import com.radhika.headyapp.model.Rankings;
import com.radhika.headyapp.model.Tax;
import com.radhika.headyapp.model.Variants;

@Database(entities = {Products.class, Categories.class, Rankings.class, RankProduct.class, Tax.class, Variants.class, Child_Category.class}, version = 1,exportSchema = false)
public abstract class ProductDatabase extends RoomDatabase {
    public abstract ProductDao productDao();
}
