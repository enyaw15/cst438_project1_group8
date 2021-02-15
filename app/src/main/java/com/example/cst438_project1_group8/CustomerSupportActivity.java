package com.example.cst438_project1_group8;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CustomerSupportActivity extends AppCompatActivity {
    public static final String CUSTOMER_SUPPORT_ACTIVITY = "CustomerSupportActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(CUSTOMER_SUPPORT_ACTIVITY, "onCreate called");
        setContentView(R.layout.activity_customer_support);
        Button create_customer_support_button = findViewById(R.id.customer_support_button);
        create_customer_support_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
