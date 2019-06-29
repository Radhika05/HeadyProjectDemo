package com.radhika.headyapp.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.radhika.headyapp.model.Categories;
import com.radhika.headyapp.model.MainPojo;
import com.radhika.headyapp.model.Products;
import com.radhika.headyapp.webservice.ProductRepositary;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private LiveData<List<Categories>> mutableLiveDataCategory;
    private LiveData<List<Products>> mutableLiveDataSubCategory;

    private List<Products> list;

    private MutableLiveData<MainPojo> productList;
    private ProductRepositary productRepositary = new ProductRepositary();
    public ProductViewModel(@NonNull Application application) {
        super(application);
    }





    public MutableLiveData<MainPojo> getHeroes(Context context) {
        if (productList == null) {
            productList =  productRepositary.loadProduct(context);
        }
        return  productList;
    }


    public LiveData<List<Categories>> getCategory(Context context) {
        if (mutableLiveDataCategory == null) {
            mutableLiveDataCategory =  productRepositary.getCategory(context);
        }
        return  mutableLiveDataCategory;
    }

    public LiveData<List<Products>> getSubCategory(Context context,int catId) {
        if (mutableLiveDataSubCategory == null) {
            mutableLiveDataSubCategory =  productRepositary.getSubCategory(context,catId);
        }
        return  mutableLiveDataSubCategory;
    }

    public List<Products> getSubCategoryA(Context context,int catId) {
        if (list == null) {
            list =  productRepositary.getSubCategoryA(context,catId);
        }
        return  list;
    }



}
