package com.example.cst438_project1_group8;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface JobsDao {
    @Query("SELECT * FROM Jobs WHERE userID == :currentUserId")
    List<Job> getAllByUserId(int currentUserId);

    @Insert
    void insert(Job job);

    @Query("DELETE FROM Jobs WHERE userId = :currentUserId and jobId = :jobId")
    abstract void delete(int currentUserId, String jobId);
}
