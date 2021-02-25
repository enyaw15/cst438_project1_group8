package com.example.cst438_project1_group8;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


import java.io.IOException;
import java.util.List;

public class JobDBInstrumentedTest {
    private JobsDao jobsDao;
    private JobDatabase jobDatabase;
    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        jobDatabase = Room.inMemoryDatabaseBuilder(context, JobDatabase.class).build();
        jobsDao = jobDatabase.jobsDao();
    }

    @After
    public void closeDb() throws IOException {
        jobDatabase.close();
    }

    @Test
    public void insertJob(){
        Job newJob = new Job();
        newJob.setCompanyName("Test Company");
        newJob.setCompanyUrl("https://test.com");
        newJob.setJobDate("00-00-2021");
        newJob.setJobDescription("This a test description");
        newJob.setJobId("XXXXXX");
        newJob.setJobTitle("C++ Programmer");
        newJob.setJobType("Entry Level Programmer");
        newJob.setJobLocation("San Jose, California");
        newJob.setJobUrl("https://test.com/jobs");
        newJob.setUserId(999);
        newJob.setSavedJobId(9999);
        jobsDao.insert(newJob);
        List<Job> jobList = jobsDao.getAllByUserId(999);
        assertEquals(true, !jobList.isEmpty());
    }
    @Test
    public void insertMultipleJobs(){
        Job newJob = new Job();
        newJob.setCompanyName("Test Company");
        newJob.setCompanyUrl("https://test.com");
        newJob.setJobDate("00-00-2021");
        newJob.setJobDescription("This a test description");
        newJob.setJobId("XXXXXX");
        newJob.setJobTitle("C++ Programmer");
        newJob.setJobType("Entry Level Programmer");
        newJob.setJobLocation("San Jose, California");
        newJob.setJobUrl("https://test.com/jobs");
        newJob.setUserId(999);
        newJob.setSavedJobId(9999);
        jobsDao.insert(newJob);
        Job secondNewJob = newJob;
        secondNewJob.setJobId("111111");
        secondNewJob.setSavedJobId(1111);
        jobsDao.insert(secondNewJob);
        assertEquals(2, jobsDao.getAllByUserId(999).size());
    }

    @Test
    public void removeJob(){
        Job newJob = new Job();
        newJob.setCompanyName("Test Company");
        newJob.setCompanyUrl("https://test.com");
        newJob.setJobDate("00-00-2021");
        newJob.setJobDescription("<html>This a test description</html>");
        newJob.setJobId("XXXXXX");
        newJob.setJobTitle("C++ Programmer");
        newJob.setJobType("Entry Level Programmer");
        newJob.setJobLocation("San Jose, California");
        newJob.setJobUrl("https://test.com/jobs");
        newJob.setUserId(999);
        newJob.setSavedJobId(9999);
        jobsDao.insert(newJob);
        jobsDao.delete(999, "XXXXXX");
        List<Job> newJobList = jobsDao.getAllByUserId(999);
        assertEquals(true, newJobList.isEmpty());
    }
}
