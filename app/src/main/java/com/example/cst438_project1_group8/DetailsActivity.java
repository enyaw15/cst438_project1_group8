package com.example.cst438_project1_group8;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

/**
 * This is an activity that allows users to see details of a clicked job so they can check information about the job they are applying for.
 * @author Lily Joh
 * @version 1.1
 * @since 1.1
 */
public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView tv_details_title = findViewById(R.id.tv_details_title);
        TextView tv_details_location = findViewById(R.id.tv_details_location);
        TextView tv_details_company_name = findViewById(R.id.tv_details_company_name);
        Button bt_details_company_url = findViewById(R.id.bt_details_company_url);
        TextView tv_details_description = findViewById(R.id.tv_details_description);

        Gson gson = new Gson();
        Job job = gson.fromJson(getIntent().getStringExtra("clicked"), Job.class);

        tv_details_title.setText(job.getJobTitle());
        tv_details_location.setText(job.getJobLocation());
        tv_details_company_name.setText(job.getCompanyName());

        // link to company website
        bt_details_company_url.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent companyUrl = new Intent(android.content.Intent.ACTION_VIEW);
                companyUrl.setData(Uri.parse(job.getCompanyUrl()));
                startActivity(companyUrl);
            }
        });
        tv_details_description.setText(job.getJobDescription());
    }
}