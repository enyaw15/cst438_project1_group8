package com.example.cst438_project1_group8;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 2)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UsersDao usersDao();

    private static UserDatabase INSTANCE;

    public static UserDatabase getUserDatabase(final Context context)
    {
        if(INSTANCE == null)
        {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, "users").build();
        }
        return INSTANCE;
    }
}
