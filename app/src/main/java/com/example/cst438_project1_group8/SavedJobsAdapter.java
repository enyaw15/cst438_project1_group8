package com.example.cst438_project1_group8;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.gson.Gson;

import java.util.Collections;
import java.util.List;

public class SavedJobsAdapter extends RecyclerView.Adapter<SavedJobsAdapter.ViewHolder> {
    List<Job> savedJobs;
    Context context;
    int currentUserId;

    public SavedJobsAdapter(List<Job> savedJobs, Context context) {
        this.savedJobs = savedJobs;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_job_row,parent,false);
        currentUserId = ((Activity) context).getIntent().getIntExtra("currentUserId", -1);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Job savedJob = savedJobs.get(position);

        holder.tv_saved_job_title.setText(savedJob.getJobTitle());
        holder.tv_saved_company_name.setText(savedJob.getCompanyName());
        holder.tv_saved_job_location.setText(savedJob.getJobLocation());
    }

    @Override
    public int getItemCount() {
        return savedJobs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_saved_job_title;
        private TextView tv_saved_company_name;
        private TextView tv_saved_job_location;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_saved_job_title = (TextView) itemView.findViewById(R.id.tv_saved_job_title);
            tv_saved_company_name = (TextView) itemView.findViewById(R.id.tv_saved_company_name);
            tv_saved_job_location = (TextView) itemView.findViewById(R.id.tv_saved_job_location);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        String clickedSavedJobId = savedJobs.get(pos).getJobId();

                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    // delete
                                    case DialogInterface.BUTTON_POSITIVE:
                                        JobsDao savedJobs = Room.databaseBuilder(context, JobDatabase.class, "jobs")
                                                .allowMainThreadQueries()
                                                .fallbackToDestructiveMigration()
                                                .build()
                                                .jobsDao();

                                        savedJobs.delete(currentUserId, clickedSavedJobId);
                                        break;
                                    // cancel
                                    case DialogInterface.BUTTON_NEGATIVE:
                                        break;
                                }
                            }
                        };

                        AlertDialog.Builder deleteAlert = new AlertDialog.Builder(context);
                        deleteAlert.setMessage("Are you sure to delete?").setPositiveButton("Delete", dialogClickListener)
                                .setNegativeButton("Cancel", dialogClickListener).show();
                    }
                }
            });
        }
    }
}
