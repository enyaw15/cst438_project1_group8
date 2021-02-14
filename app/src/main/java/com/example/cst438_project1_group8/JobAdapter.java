package com.example.cst438_project1_group8;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.ViewHolder> {
    List<Job> jobs;
    Context context;
    ArrayList<Job> filteredList = new ArrayList<>();
    int count = 0;

    public JobAdapter(List<Job> jobs, Context context) {
        for(Job job : jobs) {
            if(job.getSkill().size() != 0) {
                filteredList.add(job);
            }
        }
        this.jobs = filteredList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.job_row,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Job job = filteredList.get(position);
        List<String> skills = job.getSkill();

        holder.tv_job_title.setText(job.getJobTitle());
        holder.tv_company_name.setText(job.getCompanyName());
        holder.tv_job_location.setText(job.getJobLocation());

        // TODO: fix duplicates in skill
        String skill = "";
        for(int i = 0; i < skills.size() / 2; i++) {
            skill += skills.get(i);
            if (i != skills.size() / 2 - 1) {
                skill += ", ";
            }
        }
        holder.tv_skill_match.setText(skill);
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_job_title;
        private TextView tv_company_name;
        private TextView tv_job_location;
        private TextView tv_skill_match;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_job_title = (TextView) itemView.findViewById(R.id.tv_job_title);
            tv_company_name = (TextView) itemView.findViewById(R.id.tv_company_name);
            tv_job_location = (TextView) itemView.findViewById(R.id.tv_job_location);
            tv_skill_match = (TextView) itemView.findViewById(R.id.tv_skill_match);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Job clickedJob = jobs.get(pos);

                        Gson gson = new Gson();
                        String gsonClickedJob = gson.toJson(clickedJob);

                        Intent intent = new Intent(context, DetailsActivity.class);
                        intent.putExtra("clicked", gsonClickedJob);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}