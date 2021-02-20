package com.example.cst438_project1_group8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * This is an activity that allows users to look at the saved jobs so they can check or delete the jobs from the list.
 * @author Lily Joh
 * @version 1.1
 * @since 1.1
 */
public class SavedJobsActivity extends AppCompatActivity {

    private RecyclerView rv_saved_job;
    private SavedJobsAdapter savedJobsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_jobs);

        rv_saved_job = (RecyclerView)findViewById(R.id.rv_saved_job);
        rv_saved_job.setHasFixedSize(true);
        rv_saved_job.setLayoutManager(new LinearLayoutManager(this));

        JobsDao savedJobs = Room.databaseBuilder(this, JobDatabase.class, "jobs")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .jobsDao();

        int currentUserId = getIntent().getIntExtra("currentUserId", -1);

        savedJobsAdapter = new SavedJobsAdapter(savedJobs.getAllByUserId(currentUserId), SavedJobsActivity.this);
        rv_saved_job.setAdapter(savedJobsAdapter);

        Intent intent = new Intent(SavedJobsActivity.this, SavedJobsAdapter.class);
        intent.putExtra("currentUserId", currentUserId);
    }
}