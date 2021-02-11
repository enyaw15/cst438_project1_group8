package com.example.cst438_project1_group8;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("positions.json?")
    Call<List<Job>> getJobs();
}
