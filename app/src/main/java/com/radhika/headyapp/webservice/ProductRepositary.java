package com.radhika.headyapp.webservice;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.radhika.headyapp.model.Categories;
import com.radhika.headyapp.model.Child_Category;
import com.radhika.headyapp.model.MainPojo;
import com.radhika.headyapp.model.Products;
import com.radhika.headyapp.model.RankProduct;
import com.radhika.headyapp.model.Rankings;
import com.radhika.headyapp.model.Tax;
import com.radhika.headyapp.model.TempProduct;
import com.radhika.headyapp.model.TempProductDetails;
import com.radhika.headyapp.model.TempSubCat;
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

    private static Retrofit retrofit;
    private MutableLiveData<MainPojo> productList = new MutableLiveData<>();
    private LiveData<List<Categories>> mutableLiveDataCategory = new MutableLiveData<>();
    private LiveData<List<Products>> mutableLiveDataSubCategory = new MutableLiveData<>();

    public ProductRepositary() {
    }

    private static Retrofit getInstance() {
        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(90, TimeUnit.SECONDS)
                    .writeTimeout(90, TimeUnit.SECONDS)
                    .connectTimeout(90, TimeUnit.SECONDS)
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(ProductAPIService.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;

    }

    private static ProductAPIService getAPIService() {
        return getInstance().create(ProductAPIService.class);
    }

    public MutableLiveData<MainPojo> loadProduct(final Context context) {
        final Call<MainPojo> productCall = getAPIService().getProductDetails();
        productCall.enqueue(new Callback<MainPojo>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<MainPojo> call, Response<MainPojo> response) {
                productList.setValue(response.body());
                Log.d("onResponse", response.body().toString());

            }


            @Override
            public void onFailure(Call<MainPojo> call, Throwable t) {
                Log.d("onFailure", t.getMessage());
            }
        });
        return getResponse();
    }


    public LiveData<List<Categories>> getMutableLiveDataCategory() {
        return mutableLiveDataCategory;
    }

    public LiveData<List<Products>> getMutableLiveDataSubCategory() {
        return mutableLiveDataSubCategory;
    }

    public MutableLiveData<MainPojo> getResponse() {
        return productList;
    }

    public LiveData<List<Categories>> getCategory(Context context) {
        mutableLiveDataCategory = DatabaseManager.getInstance(context).getAppDatabase().productDao().getCategoriesdata();
        return getMutableLiveDataCategory();
    }


    public LiveData<List<TempSubCat>> getSubCategoryA(Context context, int catID) {
        return DatabaseManager.getInstance(context).getAppDatabase().productDao().getSubCategoriesdataA(catID);
    }


    public void inserData(Context context, MainPojo productList) {
        List<Variants> listVariants = new ArrayList<>();
        List<Tax> listTax = new ArrayList<>();
        List<Products> listProducts = new ArrayList<>();
        List<Child_Category> listChildCateroty = new ArrayList<>();
        List<RankProduct> listRankCategory = new ArrayList<>();
        List<Categories> listCategory = new ArrayList<>();
        Categories categories = new Categories();

        for (int i = 0; i < productList.getCategories().size(); i++) {
            categories = productList.getCategories().get(i);
            for (int j = 0; j < productList.getCategories().get(i).getProducts().size(); j++) {
                Products products = productList.getCategories().get(i).getProducts().get(j);
                for (int k = 0; k < productList.getCategories().get(i).getProducts().get(j).getVariants().size(); k++) {
                    Variants variants = productList.getCategories().get(i).getProducts().get(j).getVariants().get(k);
                    variants.setProduct_id(products.getId());
                    listVariants.add(variants);
                }
                //getting the tax
                Tax tax = productList.getCategories().get(i).getProducts().get(j).getTax();
                tax.setProduct_id(products.getId());
                listTax.add(tax);
                products.setCategory_id(categories.getId());
                listProducts.add(products);
                listCategory.add(categories);
            }
            //getting the sub category
            for (int j = 0; j < categories.getChild_cat().size(); j++) {
                Child_Category child_category = new Child_Category();
                int child = productList.getCategories().get(i).getChild_cat().get(j);
                child_category.setCategoty_id(categories.getId());
                child_category.setId(child);
                listChildCateroty.add(child_category);
            }

        }

        for (int m = 0; m < productList.getRankings().size(); m++) {
            int size = productList.getRankings().size();
            for (int j = 0; j < productList.getRankings().get(m).getRankProduct().size(); j++) {
                size = productList.getRankings().get(m).getRankProduct().size();
                RankProduct rankProduct = productList.getRankings().get(m).getRankProduct().get(j);
                rankProduct.setRankings_id(m + 1);
                listRankCategory.add(rankProduct);
            }
        }
        //insert Category
        DatabaseManager.getInstance(context).getAppDatabase().productDao().insertCategory(productList.getCategories());
        //insert Ranking
        DatabaseManager.getInstance(context).getAppDatabase().productDao().insertRanking(productList.getRankings());
        //insert Product
        DatabaseManager.getInstance(context).getAppDatabase().productDao().insertProduct(listProducts);
        //insert Variants
        DatabaseManager.getInstance(context).getAppDatabase().productDao().insertVariant(listVariants);
        //insert Tax
        DatabaseManager.getInstance(context).getAppDatabase().productDao().insertTaxes(listTax);
        //insert ProductRank
        DatabaseManager.getInstance(context).getAppDatabase().productDao().insertProductRanking(listRankCategory);
        //insert subcategory
        DatabaseManager.getInstance(context).getAppDatabase().productDao().insertSubCategory(listChildCateroty);


    }

    public List<Rankings> getRankCategory(Context applicationContext) {
        return DatabaseManager.getInstance(applicationContext).getAppDatabase().productDao().getRanking();
    }

    public LiveData<List<TempProduct>> getProduct(Context applicationContext, int prodctID) {
        return DatabaseManager.getInstance(applicationContext).getAppDatabase().productDao().getProduct(prodctID);
    }

    public LiveData<List<TempProductDetails>> getProductDetails(Context applicationContext, int prodctID) {
        return DatabaseManager.getInstance(applicationContext).getAppDatabase().productDao().getProductDetails(prodctID);
    }

    public LiveData<List<Products>> getRankWiseProduct(Context applicationContext, int rankID) {
        return DatabaseManager.getInstance(applicationContext).getAppDatabase().productDao().getRankWiseProduct(rankID);
    }
}
