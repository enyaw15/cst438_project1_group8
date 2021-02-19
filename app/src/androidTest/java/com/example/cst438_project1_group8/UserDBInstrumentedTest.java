package com.example.cst438_project1_group8;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class UserDBInstrumentedTest {
        private UsersDao usersDao;
        private UserDatabase db;

        @Before
        public void createDb() {
            Context context = ApplicationProvider.getApplicationContext();
            db = Room.inMemoryDatabaseBuilder(context, UserDatabase.class).build();
            usersDao = db.usersDao();
        }

        @After
        public void closeDb() throws IOException {
            db.close();
        }


        @Test
        public void writeUserAndReadInList() throws Exception {
            User user = new User();
            user.setUsername("username");
            usersDao.insert(user);
            List<User> byName = usersDao.findByName("username");
            assertNotNull(byName);
            assertEquals(1, byName.size());
            //this fails because we have auto generated userIds
            //assertEquals(byName.get(0), user);
            //I added new tests to test the same basic concept
            assertEquals(true, byName.get(0).getUsername().equals(user.username));
        }

        @Test
        public void checkIfUserExists() throws Exception {
            User user = new User();
            user.setUsername("example");
            user.setPassword("password");
            usersDao.insert(user);
            User userTwo = new User();
            userTwo.setUsername("example");
            userTwo.setPassword("password2");
            List<User> userByName = usersDao.findByName(userTwo.username);
            assertEquals(false, userByName.isEmpty());
            User lookUpUser = userByName.get(0);
            assertEquals(true, lookUpUser.getUsername().equals(userTwo.getUsername()));
        }

        @Test
        public void checkPassword() throws Exception{
            User user = new User();
            user.setUsername("example");
            user.setPassword("test");
            usersDao.insert(user);
            List<User> userByName = usersDao.findByName(user.getUsername());
            assertEquals(true, userByName.get(0).getPassword().equals(user.getPassword()));
        }
}
