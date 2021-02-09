package com.example.cst438_project1_group8;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobActivity extends AppCompatActivity {

    private RecyclerView rv_job;
    private List<Job>jobs;
    private JsonPlaceHolderApi apiInterface;
    private JobAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);

        rv_job = (RecyclerView)findViewById(R.id.rv_job);
        rv_job.setHasFixedSize(true);
        rv_job.setLayoutManager(new LinearLayoutManager(this));

        apiInterface=RetrofitInstance.getRetrofitInstance().create(JsonPlaceHolderApi.class);
        Call<List<Job>> call = apiInterface.getJobs();
        call.enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                jobs = response.body();
                adapter = new JobAdapter(jobs, JobActivity.this);
                rv_job.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {

            }
        });
    }
}