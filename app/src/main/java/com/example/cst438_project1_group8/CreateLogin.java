package com.example.cst438_project1_group8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CreateLogin extends AppCompatActivity {
    TextView usernameField;
    TextView passwordField;
    Button signup;
    String username;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_login);
        usernameField = findViewById(R.id.create_login_username_field);
        passwordField = findViewById(R.id.create_login_password_field);
        signup = findViewById(R.id.create_login_button);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = usernameField.getText().toString();
                password = usernameField.getText().toString();
                /*TODO: - Get usernames from DB
                TODO: - Verify username is valid
                TODO: - Write to DB
                 */
                User newUser = new User();
                newUser.username = username;
                newUser.password = password;
            }
        });
    }

}