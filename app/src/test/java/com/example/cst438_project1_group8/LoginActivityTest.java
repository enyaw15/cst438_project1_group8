package com.example.cst438_project1_group8;

import android.content.Context;
import android.util.Log;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static org.junit.Assert.*;

public class LoginActivityTest {
    @Test
    public void checkUsernameTest(){
        //This doesn't work properly. Not mocked.
        User use = new User();
        use.setPassword("Musashi");
        use.setUsername("Kojiro");
        Context context = new LoginActivity().getApplicationContext();
        UsersDao usersDao = UserDatabase.getUserDatabase(context).usersDao();
        usersDao.insert(use);
        List<User> users = usersDao.getAll();
        LoginActivity loginActivity = new LoginActivity();
        assertTrue(loginActivity.checkForUser("Kojiro", users).first);
    }
}
