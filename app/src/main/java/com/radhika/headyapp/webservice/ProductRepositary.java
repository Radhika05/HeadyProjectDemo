package com.radhika.headyapp.webservice;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.radhika.headyapp.model.Categories;
import com.radhika.headyapp.model.MainPojo;
import com.radhika.headyapp.model.Products;
import com.radhika.headyapp.model.RankProduct;
import com.radhika.headyapp.model.Tax;
import com.radhika.headyapp.model.Variants;
import com.radhika.headyapp.roomdatabase.DatabaseManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductRepositary {

    private MutableLiveData<MainPojo> productList = new MutableLiveData<>();
    private static Retrofit retrofit;

    public ProductRepositary() {
    }

    private static Retrofit getInstance(){
       if(retrofit==null){
           OkHttpClient okHttpClient = new OkHttpClient.Builder()
                   .readTimeout(90, TimeUnit.SECONDS)
                   .writeTimeout(90,TimeUnit.SECONDS)
                   .connectTimeout(90,TimeUnit.SECONDS)
                   .build();
           retrofit= new Retrofit.Builder()
                   .baseUrl(ProductAPIService.BASE_URL)
                   .client(okHttpClient)
                   .addConverterFactory(GsonConverterFactory.create())
                   .build();
       }
       return retrofit;

    }

    private static ProductAPIService getAPIService(){
        return getInstance().create(ProductAPIService.class);
    }

    public MutableLiveData<MainPojo> loadProduct(final Context context){
        final Call<MainPojo> productCall = getAPIService().getProductDetails();
        productCall.enqueue(new Callback<MainPojo>() {
            @Override
            public void onResponse(Call<MainPojo> call, Response<MainPojo> response) {
                productList.setValue(response.body());
                Log.d("onResponse",response.body().toString());
                 List<Categories> categories = productList.getValue().getCategories();


                List<Products> products = new ArrayList<>();
                for(int i=0;i<=categories.size()-1;i++){
                    List productsItem = categories.get(i).getProducts();
                    products.addAll(productsItem);
                }

                List<Variants> variants = new ArrayList<>();;
                for(int i=0;i<=products.size()-1;i++){
                    List variantsItem = products.get(i).getVariants();
                    if(variantsItem!=null){
                        variants.addAll(variantsItem);
                    }
                }

                List<Tax> taxes = new ArrayList<>();;
                for(int i=0;i<=products.size()-1;i++){
                    Tax tax =  products.get(i).getTax();
                    if(tax!=null){
                        taxes.add(tax);
                    }
                }

                List<RankProduct> rankProducts = new ArrayList<>();;
                for(int i = 0;i<=productList.getValue().getRankings().size()-1;i++){
                    List rankProduct = productList.getValue().getRankings().get(i).getRankProduct();
                    if(rankProduct!=null){
                        rankProducts.addAll(rankProduct);
                    }
                }

                //insert Category
                DatabaseManager.getInstance(context).getAppDatabase().productDao().insertCategory(productList.getValue().getCategories());
                //insert Ranking
                DatabaseManager.getInstance(context).getAppDatabase().productDao().insertRanking(productList.getValue().getRankings());
                //insert Product
                DatabaseManager.getInstance(context).getAppDatabase().productDao().insertProduct(products);
                //insert Variants
                DatabaseManager.getInstance(context).getAppDatabase().productDao().insertVariant(variants);
                //insert Tax
                DatabaseManager.getInstance(context).getAppDatabase().productDao().insertTaxes(taxes);
                //insert ProductRank
                DatabaseManager.getInstance(context).getAppDatabase().productDao().insertProductRanking(rankProducts);
            }

            @Override
            public void onFailure(Call<MainPojo> call, Throwable t) {
                Log.d("onFailure",t.getMessage());
            }
        });
        return productList;
    }
}
