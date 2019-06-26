package com.radhika.headyapp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.radhika.headyapp.model.MainPojo;
import com.radhika.headyapp.webservice.ProductAPIService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductViewModel extends AndroidViewModel {

    private MutableLiveData<MainPojo> productList;

    public ProductViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<MainPojo> getHeroes() {
        //if the productList is null
        if (productList == null) {
            productList = new MutableLiveData<MainPojo>();
            //we will load it asynchronously from server in this method
            loadHeroes();
        }
        return productList;
    }

    public void loadHeroes(){

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(90, TimeUnit.SECONDS)
                .writeTimeout(90,TimeUnit.SECONDS)
                .connectTimeout(90,TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ProductAPIService.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductAPIService api = retrofit.create(ProductAPIService.class);
        Call<MainPojo> productCall = api.getProductDetails();
        productCall.enqueue(new Callback<MainPojo>() {
            @Override
            public void onResponse(Call<MainPojo> call, Response<MainPojo> response) {
                productList.setValue(response.body());
                Log.d("onResponse",response.body().toString());
            }

            @Override
            public void onFailure(Call<MainPojo> call, Throwable t) {
                Log.d("onFailure",t.getMessage());
            }
        });


     /*   productCall.enqueue(new Callback<List<MainPojo>>() {
            @Override
            public void onResponse(Call<List<MainPojo>> call, Response<List<MainPojo>> response) {
                productList.setValue(response.body());
                Log.d("onResponse",response.body().toString());
            }

            @Override
            public void onFailure(Call<List<MainPojo>> call, Throwable t) {
                Log.d("onFailure",t.getMessage());
            }
        });*/

    }
}
