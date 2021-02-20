package com.example.cst438_project1_group8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * This is an activity that allows users to select the next screen so they can search jobs or see the saved jobs.
 * @author Lily Joh
 * @version 1.1
 * @since 1.1
 */
public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button bt_home_search = findViewById(R.id.bt_home_search);
        Button bt_home_my_jobs = findViewById(R.id.bt_home_my_jobs);

        int currentUserId = getIntent().getIntExtra("currentUserId", -1);

        bt_home_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, JobActivity.class);
                intent.putExtra("currentUserId", currentUserId);
                startActivity(intent);
            }
        });

        bt_home_my_jobs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SavedJobsActivity.class);
                intent.putExtra("currentUserId", currentUserId);
                startActivity(intent);
            }
        });
    }
}