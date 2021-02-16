package com.example.cst438_project1_group8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CreateLogin extends AppCompatActivity {
    TextView usernameField;
    TextView passwordField;
    Button signup;
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
        currentUsers = Room.databaseBuilder(this, UserDatabase.class, "users")
                .allowMainThreadQueries()
                .build()
                .usersDao();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = usernameField.getText().toString();
                password = usernameField.getText().toString();
                /*TODO: switch context back to landing page
                 */
                User newUser = new User();
                newUser.username = username;
                newUser.password = password;

                List<User> checkUsername = currentUsers.findByName(username);
                if(!checkUsername.isEmpty()){
                    toaster("Username already exists!");
                }else{
                    currentUsers.insert(newUser);
                    toaster("Successfully created login!");
                }
            }
        });
    }
    private void toaster(String message){
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
}