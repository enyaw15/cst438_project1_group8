package com.example.cst438_project1_group8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * This is an activity that allows a user to create a login so they can access the rest of the app.
 * @author Chris Estes
 * @version 1.1
 * @since 02-04-2021
 */
public class CreateLogin extends AppCompatActivity {
    TextView usernameField;
    TextView passwordField;
    Button signup;
    Button returnButton;
    String username;
    String password;
    UsersDao currentUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_login);
        usernameField = findViewById(R.id.create_login_username_field);
        passwordField = findViewById(R.id.create_login_password_field);
        signup = findViewById(R.id.create_login_button);
        returnButton = findViewById(R.id.create_login_return_button);
        currentUsers = Room.databaseBuilder(this, UserDatabase.class, "users")
                .allowMainThreadQueries()
                .build()
                .usersDao();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get the text the user entered
                username = usernameField.getText().toString();
                password = passwordField.getText().toString();
                //create a new user object with it
                User newUser = new User();
                newUser.setUsername(username);
                newUser.setPassword(password);
                //check if username is already taken
                List<User> checkUsername = currentUsers.findByName(username);
                if(!checkUsername.isEmpty()){
                    toaster("Username already exists!");
                }
                //otherwise add the user to the DB and return to the login activity
                else{
                    currentUsers.insert(newUser);
                    toaster("Successfully created login!");
                    //Switch back to Login Activity
                    returnToLogin();
                }
            }
        });
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnToLogin();
            }
        });
    }
    /**
     * This method produces a toast message.
     * @param message is the message that we want to be displayed as a toast.
     */
    private void toaster(String message){
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
    /**
     * This method generates an intent for the login activity and starts that activity.
     */
    private void returnToLogin(){
        Intent intent = new Intent(CreateLogin.this, LoginActivity.class);
        startActivity(intent);
    }
}