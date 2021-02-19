package com.example.cst438_project1_group8;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
/**
 * This class is the user object that contains a username and password
 * @author Wayne Hayden, Francisco Hernandez, Chris Estes
 * @version 1.2
 * @since 02-04-2021
 */
@Entity(tableName = "users")
public class User {

    @PrimaryKey(autoGenerate = true)
    public int userId;

    public String username;
    public String password;

    /**
     * Getter for username
     * @return string of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for username
     * @param username string
     */
    public void setUsername(String username){
        this.username = username;
    }

    /**
     * Getter for password
     * @return string of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for password
     * @param password string
     */
    public void setPassword(String password) {
        this.password = password;
    }



}
