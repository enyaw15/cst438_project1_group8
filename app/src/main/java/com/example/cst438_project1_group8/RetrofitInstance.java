package com.example.cst438_project1_group8;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This class is an instance of Retrofit to issue network requests to a REST API and convert job objects.
 * @author Lily Joh
 * @version 1.1
 * @since 1.1
 */
public class RetrofitInstance {
    private static Retrofit retrofit=null;
    private static final String BASE_URL = "https://jobs.github.com/";
    public static Retrofit getRetrofitInstance(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
