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
import com.radhika.headyapp.model.Variants;
import com.radhika.headyapp.roomdatabase.DatabaseManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    public LiveData<List<Categories>> loadProduct(final Context context){
        final Call<MainPojo> productCall = getAPIService().getProductDetails();
        productCall.enqueue(new Callback<MainPojo>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<MainPojo> call, Response<MainPojo> response) {
                productList.setValue(response.body());
                Log.d("onResponse",response.body().toString());


                List<Variants> listVariants = new ArrayList<>();
                List<Tax> listTax = new ArrayList<>();
                List<Products> listProducts =  new ArrayList<>();
                List<Child_Category> listChildCateroty=  new ArrayList<>();
                List<RankProduct> listRankCategory = new ArrayList<>();
                List<Categories> listCategory = new ArrayList<>();
                List<Rankings> listRankProduct = new ArrayList<>();
                Categories categories = new Categories();



                 for(int i = 0; i< Objects.requireNonNull(productList.getValue()).getCategories().size(); i++){
                     categories = productList.getValue().getCategories().get(i);
                     for(int j=0;j<productList.getValue().getCategories().get(i).getProducts().size();j++){
                         Products products = productList.getValue().getCategories().get(i).getProducts().get(j);{
                             for(int k=0;k<productList.getValue().getCategories().get(i).getProducts().get(j).getVariants().size();k++){
                                Variants variants =  productList.getValue().getCategories().get(i).getProducts().get(j).getVariants().get(k);
                                variants.setProduct_id(products.getId());
                                 listVariants.add(variants);
                             }
                             //getting the tax
                             Tax tax = productList.getValue().getCategories().get(i).getProducts().get(j).getTax();
                             tax.setProduct_id(products.getId());
                             listTax.add(tax);
                             products.setCategory_id(categories.getId());
                             listProducts.add(products);
                             listCategory.add(categories);


                         }
                     }
                     //getting the sub category
                     for(int j=0;j<categories.getChild_cat().size();j++){
                         Child_Category child_category = new Child_Category();
                         int child = productList.getValue().getCategories().get(i).getChild_cat().get(j);
                         child_category.setCategoty_id(categories.getId());
                         child_category.setId(child);
                         listChildCateroty.add(child_category);
                     }

                 }

                 //getting the Ranking data

                int size = productList.getValue().getRankings().size();
                    Log.d("size", String.valueOf(size));
                    for(int i=0;i< productList.getValue().getRankings().size();i++)
                    {
                        Rankings rankings = productList.getValue().getRankings().get(i);
                    for(int j=0;j<productList.getValue().getRankings().get(i).getRankProduct().size();j++)
                    {
                        RankProduct rankProduct = productList.getValue().getRankings().get(i).getRankProduct().get(j);
                        rankProduct.setCatergory_Id(productList.getValue().getRankings().get(i).getIds()+1);
                        listRankCategory.add(rankProduct);
                        listRankProduct.add(rankings);
                    }
                }
                //insert Category
                DatabaseManager.getInstance(context).getAppDatabase().productDao().insertCategory(productList.getValue().getCategories());
                //insert Ranking
                DatabaseManager.getInstance(context).getAppDatabase().productDao().insertRanking(listRankProduct);
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

            @Override
            public void onFailure(Call<MainPojo> call, Throwable t) {
                Log.d("onFailure",t.getMessage());
            }
        });
        return DatabaseManager.getInstance(context).getAppDatabase().productDao().getCategories();
    }
}
