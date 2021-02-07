package com.example.cst438_project1_group8;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    public static final String LOGINACTIVITY = "LoginActivity";
    public static final String USER = "User";
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
                EditText username = findViewById(R.id.username);
                String name = username.getText().toString();
                boolean userSuccess = false;
                EditText password = findViewById(R.id.password);
                String pword = password.getText().toString();
                boolean passwordSuccess = false;
                String msg = "";
                boolean check = false;

                //Implement code that checks for proper username and password here.
                /*
                List<userClass> users = dao.getRoom(LoginActivity.this).dao().getAllUsers();
                for(int i = 0; i < users.size(); i++){
                    if(name.equals(users.get(i).getUsername)){
                        userSuccess = true;
                        if(pword.equals(users.get(i).getPassword)){
                            passwordSuccess = true;
                            break; //User is correct, password is correct. End loop.
                        } else {
                            break; //User is correct, password is wrong. End loop
                        }
                    }
                }
                */
                    if(!(passwordSuccess)){
                    msg += "Password is incorred.\n";
                    check = true;
                }
                if(!(userSuccess)){
                    msg += "User doesn't exist.\n";
                    check = true;
                }
                if(!(check)){
                    toast = Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_LONG);
                    toast.show();;
                } else {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString(USER, name);
                    intent.putExtras(bundle);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}
