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

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    public static final String LOGINACTIVITY = "LoginActivity";
    public static final String LOGIN = "Login";
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
                EditText username = findViewById(R.id.login_account_username);
                String name = username.getText().toString();
                Log.d(LOGINACTIVITY, "Username is " + name);
                EditText password = findViewById(R.id.login_account_password);
                String pword = password.getText().toString();
                Log.d(LOGINACTIVITY, "Password is " + pword);
                String msg = "";
                boolean check = false;

                //Implement code that checks for proper username and password here.
                /*
                List<userClass> users = dao.getRoom(LoginActivity.this).dao().getAllUsers();
                Pair<Boolean, Integer> userCheck = checkForUser(name, users);
                if(userCheck.getKey()){
                    if(checkForPassword(pword, userCheck.getValue(), users)){
                        check = true;
                    } else {
                        msg += "Password is incorrect.";
                    }
                } else{
                    msg = "Username is incorrect.\n";
                }
                */
                if(!(check)){
                    toast = Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_LONG);
                    toast.show();;
                } else {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString(LOGIN, name);
                    intent.putExtras(bundle);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    /*
    * Checks to see if user exists.
    * Currently commented out due to no room database implementation yet.
     */
    public Pair<Boolean, Integer> checkForUser(String name/*,List<Users> users.*/){
        /*
        for(int i = 0; i < users.size(); i++){
            if(name.equals(users.get(i).getUsername)){
                userSuccess = true;
                return new Pair<Boolean, Integer>(true, i);
            }
        }
       */
        return new Pair<Boolean, Integer>(false, 0);
    }

    /*
    * Checks to see if password is correct for user.
    * Currently comment out due to no room database implementation yet.
     */
    public boolean checkForPassword(String password, int index/*,List<Users> users.*/){
        /*
        if(users.get(index).getPassword.equals(password)){
            return true;
        }
       */
        return false;
    }
}
