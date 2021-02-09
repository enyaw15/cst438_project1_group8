package com.example.cst438_project1_group8;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView tv_details_title = findViewById(R.id.tv_details_title);
        TextView tv_details_location = findViewById(R.id.tv_details_location);
        TextView tv_details_company_name = findViewById(R.id.tv_details_company_name);
        TextView tv_details_company_url = findViewById(R.id.tv_details_company_url);
        TextView tv_details_description = findViewById(R.id.tv_details_description);

        Gson gson = new Gson();
        Job job = gson.fromJson(getIntent().getStringExtra("clicked"), Job.class);

        tv_details_title.setText("Job Title: " + job.getJobTitle());
        tv_details_location.setText("Location: " + job.getJobLocation());
        tv_details_company_name.setText("Company: " + job.getCompanyName());
        tv_details_company_url.setText("Website: " + job.getCompanyUrl());
        tv_details_description.setText(job.getJobDescription());
//        String skills = "";
//        for(String skill : job.getSkill()) {
//            skills += skill + " ";
//        }
//        tv_details_title.setText(skills);
    }
}