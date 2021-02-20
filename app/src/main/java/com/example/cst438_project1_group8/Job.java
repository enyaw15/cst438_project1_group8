package com.example.cst438_project1_group8;

import android.text.Html;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * This class is the job object that contains job information, savedJobId, userId, and jobId
 * @author Lily Joh
 * @version 1.1
 * @since 1.1
 */
@Entity(tableName = "jobs")
public class Job {

    @PrimaryKey(autoGenerate = true)
    private int savedJobId;

    private int userId;

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

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public void setJobUrl(String jobUrl) {
        this.jobUrl = jobUrl;
    }

    public void setJobDate(String jobDate) {
        this.jobDate = jobDate;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public int getSavedJobId() {
        return savedJobId;
    }

    public void setSavedJobId(int savedJobId) {
        this.savedJobId = savedJobId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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
