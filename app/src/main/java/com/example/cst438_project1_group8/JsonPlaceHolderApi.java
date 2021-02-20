package com.example.cst438_project1_group8;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * This is a JSONPlaceholder by retrofit. There are four API endpoints consumed using Query.
 * @author Lily Joh
 * @version 1.1
 * @since 1.1
 */
public interface JsonPlaceHolderApi {

    @GET("positions.json?")
    Call<List<Job>> getJobs(@Query("description") String term,
                            @Query("location") String city,
                            @Query("lat") String lat,
                            @Query("long") String lon);
}
