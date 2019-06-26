package com.radhika.headyapp.webservice;

import com.radhika.headyapp.model.MainPojo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductAPIService {
    String BASE_URL = "https://stark-spire-93433.herokuapp.com/json/";

    @GET(BASE_URL)
    Call<MainPojo> getProductDetails();
    //Call<List<MainPojo>> getProductDetails();


}
