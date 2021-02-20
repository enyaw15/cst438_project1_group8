package com.example.cst438_project1_group8;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * This class is a job database for saved jobs
 * @author Lily Joh
 * @version 1.1
 * @since 1.1
 */
@Database(entities = {Job.class}, version = 1)
public abstract class JobDatabase extends RoomDatabase {
    public abstract JobsDao jobsDao();

    private static JobDatabase INSTANCE;

    public static JobDatabase getUserDatabase(final Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), JobDatabase.class, "users").build();
        }
        return INSTANCE;
    }
}
