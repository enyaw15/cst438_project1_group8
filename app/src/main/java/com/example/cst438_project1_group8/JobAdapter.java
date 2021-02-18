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

import java.util.List;


public class JobAdapter extends RecyclerView.Adapter<JobAdapter.ViewHolder> {
    List<Job> jobs;
    Context context;

    public JobAdapter(List<Job> jobs, Context context) {
        this.jobs = jobs;
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
        Job job = jobs.get(position);
        holder.tv_job_title.setText(job.getJobTitle());
        holder.tv_company_name.setText(job.getCompanyName());
        holder.tv_job_location.setText(job.getJobLocation());
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_job_title;
        private TextView tv_company_name;
        private TextView tv_job_location;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_job_title = (TextView) itemView.findViewById(R.id.tv_job_title);
            tv_company_name = (TextView) itemView.findViewById(R.id.tv_company_name);
            tv_job_location = (TextView) itemView.findViewById(R.id.tv_job_location);

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
