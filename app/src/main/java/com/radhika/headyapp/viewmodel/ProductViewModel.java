package com.radhika.headyapp.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.radhika.headyapp.model.Categories;
import com.radhika.headyapp.webservice.ProductRepositary;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private LiveData<List<Categories>> productList;
    private ProductRepositary productRepositary = new ProductRepositary();

    public ProductViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Categories>> getHeroes(Context context) {
        if (productList == null) {
            productList = productRepositary.loadProduct(context);
        }
        return productList;
    }


}
