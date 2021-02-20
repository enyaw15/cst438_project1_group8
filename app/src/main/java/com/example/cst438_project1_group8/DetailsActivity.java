package com.example.cst438_project1_group8;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.google.gson.Gson;

import java.util.List;

/**
 * This is an activity that allows users to see details of a clicked job so they can check information about the job and save it to a saved job list.
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
        ToggleButton tb_save_job = findViewById(R.id.tb_save_job);

        JobsDao savedJobs = Room.databaseBuilder(this, JobDatabase.class, "jobs")
                .allowMainThreadQueries()
                .build()
                .jobsDao();

        Gson gson = new Gson();
        Job job = gson.fromJson(getIntent().getStringExtra("clicked"), Job.class);

        int currentUserId = getIntent().getIntExtra("currentUserId", -1);
        job.setUserId(currentUserId);

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

        // change the status of the save button depending on whether the job is on the saved list
        if(isPresent(savedJobs, currentUserId, job)) {
            tb_save_job.setChecked(true);
        } else {
            tb_save_job.setChecked(false);
        }

        tb_save_job.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // if the job is on the saved list, delete it from the list
                if(isPresent(savedJobs, currentUserId, job)) {
                    savedJobs.delete(currentUserId, job.getJobId());
                }
                // if not on the list, insert it to the list
                else {
                    savedJobs.insert(job);
                }
            }
        });
    }

    /**
     * This method checks if the current user has saved the current job or not.
     * @param savedJobs a list of all saved jobs in the "jobs" table
     * @param currentUserId ID of the currently logged in user
     * @param job a job object clicked by the user
     * @return if the job is on the saved list, return true, if not, return false
     */
    public Boolean isPresent(JobsDao savedJobs, int currentUserId, Job job) {
        List<Job> savedJobsByUser = savedJobs.getAllByUserId(currentUserId);
        for(Job j : savedJobsByUser) {
            if(String.valueOf(j.getJobId()).equals(String.valueOf(job.getJobId()))) {
                return true;
            }
        }
        return false;
    }
}