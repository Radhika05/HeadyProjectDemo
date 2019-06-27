package com.radhika.headyapp.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.radhika.headyapp.model.MainPojo;
import com.radhika.headyapp.webservice.ProductRepositary;

public class ProductViewModel extends AndroidViewModel {

    private MutableLiveData<MainPojo> productList;
    private ProductRepositary productRepositary = new ProductRepositary();

    public ProductViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<MainPojo> getHeroes(Context context) {
        if (productList == null) {
            productList = productRepositary.loadProduct(context);
        }
        return productList;
    }


}
