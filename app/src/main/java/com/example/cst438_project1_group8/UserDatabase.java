package com.example.cst438_project1_group8;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
/**
 * Class to represent room database users
 * the users database stores objects of the User class
 * the users database is accessed through the UsersDao lass
 * @author Wayne Hayden
 * @version 1.0
 * @since 02-04-2021
 */
@Database(entities = {User.class}, version = 2)
public abstract class UserDatabase extends RoomDatabase {
    /**
     * abstract constructor
     */
    public abstract UsersDao usersDao();

    /**
     * the single instance of userdatabase
     */
    private static UserDatabase INSTANCE;

    /**
     * Singleton for the users database
     * @param context the application context
     * @return the instance of Userdatabase representing the users table
     */
    public static UserDatabase getUserDatabase(final Context context)
    {
        if(INSTANCE == null)
        {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, "users").build();
        }
        return INSTANCE;
    }
}
