package com.example.cst438_project1_group8;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    public static final String LOGIN_ACTIVITY = "LoginActivity";
    public static final String LOGIN = "Login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOGIN_ACTIVITY, "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

       UsersDao nameOfUsersDaoObject = Room.databaseBuilder(LoginActivity.this,
                UserDatabase.class, "users")
                .allowMainThreadQueries().build().usersDao();
        User user = new User();
        user.setUsername("Haku");
        user.setPassword("Kuon");
        user.userId = 0;
        nameOfUsersDaoObject.insert(user);
        Button create_login_button = findViewById(R.id.login_button);
        create_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(LOGIN_ACTIVITY, "onClick called");
                EditText username = findViewById(R.id.login_account_username);
                String name = username.getText().toString();
                Log.d(LOGIN_ACTIVITY, "Username is " + name);
                EditText password = findViewById(R.id.login_account_password);
                String pword = password.getText().toString();
                Log.d(LOGIN_ACTIVITY, "Password is " + pword);
                String msg = "";
                boolean check = false;
                List<User> users = nameOfUsersDaoObject.getAll();
                Toast toast = Toast.makeText(LoginActivity.this, ""+users.size(), Toast.LENGTH_LONG);
                toast.show();
                Log.d(LOGIN_ACTIVITY, "Users size is " + users.size());
                Pair<Boolean, Integer> userCheck = checkForUser(name, users);
                if(userCheck.first){
                    if(checkForPassword(pword, userCheck.second, users)){
                        check = true;
                    } else {
                        msg += "Password is incorrect.";
                    }
                } else{
                    msg = "Username is incorrect.";
                }
                if(!(check)){
                   Toast t = Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_LONG);
                   t.show();;
                } else {
                    Intent intent = new Intent(LoginActivity.this, JobActivity.class);
                    startActivity(intent);
                }
            }
        });

        Button create_account_switch_button = findViewById(R.id.create_account_switch_button);
        create_account_switch_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast t = Toast.makeText(LoginActivity.this,
                        "Create account activity is being called", Toast.LENGTH_LONG);
                t.show();
                //Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.Class);
                //startActivity(intent);
            }
        });
    }

    public Pair<Boolean, Integer> checkForUser(String name,List<User> users){
        Log.d(LOGIN_ACTIVITY, "checkForUser called");
        for(int i = 0; i < users.size(); i++){
            if(name.equals(users.get(i).getUsername())){
                return new Pair<Boolean, Integer>(true, i);
            }
        }
        return new Pair<Boolean, Integer>(false, 0);
    }

    public boolean checkForPassword(String password, int index,List<User> users){
        Log.d(LOGIN_ACTIVITY, "checkForPassword called");
        if(users.get(index).getPassword().equals(password)) {
            return true;
        }
       return false;
    }
}
