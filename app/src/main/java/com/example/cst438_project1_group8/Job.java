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

    String[] skills = {"Python", "C++", "Java", "JavaScript", "HTML", "CSS", "API"};
    List<String> skillMatch = new ArrayList<String>();

    public List<String> getSkill() {
        for(int i = 0; i < skills.length; i++) {
            // check if skills are included in job description text
            if (getJobDescription().toLowerCase().contains(skills[i].toLowerCase())) {
                // if included, append the skill
                skillMatch.add(skills[i]);
            }
        }
        return skillMatch;
    }
}
