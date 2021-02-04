package com.example.cst438_project1_group8;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {

    @PrimaryKey
    public int userId;

    public String username;
    public String password;
}
