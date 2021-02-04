package com.example.cst438_project1_group8;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    public static final String LOGINACTIVITY = "LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOGINACTIVITY, "onCreate called");
        super.onCreate(savedInstanceState);
    }
}
