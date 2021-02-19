package com.example.cst438_project1_group8;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
/**
 * Text here
 * @author Wayne Hayden
 * @version 1.0
 * @since 02-04-2021
 */
@Dao
public interface UsersDao {
    @Query("SELECT * FROM Users")
    List<User> getAll();

    @Query("SELECT * FROM Users WHERE userID IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM Users WHERE username LIKE :username LIMIT 1")
    List<User> findByName(String username);

    @Insert
    void insertAll(User... users);

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);
}
