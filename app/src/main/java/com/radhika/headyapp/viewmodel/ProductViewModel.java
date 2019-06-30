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
import com.radhika.headyapp.model.Rankings;
import com.radhika.headyapp.model.TempProduct;
import com.radhika.headyapp.model.TempProductDetails;
import com.radhika.headyapp.model.TempSubCat;
import com.radhika.headyapp.webservice.ProductRepositary;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private LiveData<List<Categories>> mutableLiveDataCategory;
    private List<TempSubCat> list;
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

    public List<TempSubCat> getSubCategoryA(Context context,int catId) {
        if (list == null) {
            list =  productRepositary.getSubCategoryA(context,catId);
        }
        return  list;
    }

    public void inserData(Context applicationContext, MainPojo categories) {
        productRepositary.inserData(applicationContext,categories);
    }

    public List<Rankings> getRankCategory(Context applicationContext) {
        return productRepositary.getRankCategory(applicationContext);
    }

    public LiveData<List<TempProduct>> getProduct(Context applicationContext,int productId) {
        return productRepositary.getProduct(applicationContext,productId);
    }

    public LiveData<List<TempProductDetails>> getProductDetails(Context applicationContext, int productId) {
        return productRepositary.getProductDetails(applicationContext,productId);
    }

    public LiveData<List<Products>> getRankWiseProduct(Context applicationContext, int productId){
        return productRepositary.getRankWiseProduct(applicationContext,productId);
    }
}
