package com.example.cst438_project1_group8;

import android.text.Html;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Job {

    @SerializedName("id")
    private String jobId;

    @SerializedName("type")
    private String jobType;

    @SerializedName("url")
    private String jobUrl;

    @SerializedName("created_at")
    private String jobDate;

    @SerializedName("company")
    private String companyName;

    @SerializedName("company_url")
    private String companyUrl;

    @SerializedName("location")
    private String jobLocation;

    @SerializedName("title")
    private String jobTitle;

    @SerializedName("description")
    private String jobDescription;

    public String getJobId() {
        return jobId;
    }

    public String getJobType() {
        return jobType;
    }

    public String getJobUrl() {
        return jobUrl;
    }

    public String getJobDate() {
        return jobDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getJobDescription() {
        return Html.fromHtml(jobDescription).toString();
    }

}
