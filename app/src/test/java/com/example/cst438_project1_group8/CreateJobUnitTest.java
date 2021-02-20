package com.example.cst438_project1_group8;

import android.text.Html;
import android.text.Spanned;

import androidx.annotation.NonNull;

import org.junit.Test;
import static org.junit.Assert.*;

public class CreateJobUnitTest {
    @Test
    public void createNewJob(){
        Job newJob = new Job();
        newJob.setCompanyName("Test Company");
        newJob.setCompanyUrl("https://test.com");
        newJob.setJobDate("00-00-2021");
        newJob.setJobId("XXXXXX");
        newJob.setJobTitle("C++ Programmer");
        newJob.setJobType("Entry Level Programmer");
        newJob.setJobLocation("San Jose, California");
        newJob.setJobUrl("https://test.com/jobs");
        newJob.setUserId(999);
        newJob.setSavedJobId(9999);
        assertEquals(true, newJob.getCompanyName().equals("Test Company"));
        assertEquals(true, newJob.getCompanyUrl().equals("https://test.com"));
        assertEquals(true, newJob.getJobUrl().equals("https://test.com/jobs"));
        assertNotEquals(true, newJob.getJobDate().equals("tomorrow"));
        assertNotEquals(true, newJob.getJobId().equals("10000"));
        assertEquals(true, newJob.getJobTitle().equals("C++ Programmer"));
        assertEquals(true, newJob.getJobType().equals("Entry Level Programmer"));
        assertNotEquals(true, newJob.getJobLocation().equals("New York, New York"));
        assertEquals(999, newJob.getUserId());
        assertNotEquals(1000, newJob.getSavedJobId());
    }
}
