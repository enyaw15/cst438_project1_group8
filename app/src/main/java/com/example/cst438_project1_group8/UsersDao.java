package com.example.cst438_project1_group8;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
/**
 * Data Access Object for the users table
 * @author Wayne Hayden
 * @version 1.0
 * @since 02-04-2021
 */
@Dao
public interface UsersDao {
    /**
     * method for getting all users in the users table
     * @return a list of users representing the entire table
     */
    @Query("SELECT * FROM Users")
    List<User> getAll();

    /**
     * method for getting a selection of users by userID from the users table
     * @param userIds array of all the userIDs to get from the table
     * @return List<User> of all users with matching userIDs
     */
    @Query("SELECT * FROM Users WHERE userID IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    /**
     * method for getting a user by username from the user table
     * @param username the username to search for in the usertable
     * @return List<User> A list containing at most one user with matching username
     */
    @Query("SELECT * FROM Users WHERE username LIKE :username LIMIT 1")
    List<User> findByName(String username);

    /**
     * method for inserting users into the users table
     * @param users ellipses allowing the insertion of unlimited number of users
     */
    @Insert
    void insertAll(User... users);

    /**
     * method for inserting a single user into the users table
     * @param user user to be added to the users table
     */
    @Insert
    void insert(User user);

    /**
     * method for deleting a single user from the users table
     * @param user the user object to be deleted
     */
    @Delete
    void delete(User user);
}
