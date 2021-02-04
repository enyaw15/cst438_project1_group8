package com.example.cst438_project1_group8;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    public static final String LOGINACTIVITY = "LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOGINACTIVITY, "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        Button create_login_button = findViewById(R.id.login_button);
        create_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(LOGINACTIVITY, "onClick called");
                Toast toast = Toast.makeText(LoginActivity.this, "Login Toast", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}
