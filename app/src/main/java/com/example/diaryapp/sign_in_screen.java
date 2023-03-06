package com.example.diaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class sign_in_screen extends AppCompatActivity {

    EditText username, password;
    Button btnlogin, btncreate;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_screen);

        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        btnlogin = (Button) findViewById(R.id.btnsignin1);
        btncreate = (Button) findViewById(R.id.btnCreate);
        DB = new DBHelper(this);

        btnlogin.setOnClickListener(v -> {
            String user = username.getText().toString();
            String pass = password.getText().toString();
            if (user.equals("")||pass.equals(""))
                Toast.makeText(sign_in_screen.this,"please enter all fields", Toast.LENGTH_SHORT).show();
            else{
                Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                if(checkuserpass){
                    Toast.makeText(sign_in_screen.this,"Sign in successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(sign_in_screen.this,"invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }


        });
        btncreate.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),create_new.class);
            startActivity(intent);
        });
    }
    int counter=0;
    @Override
    public void onBackPressed() {
        Toast.makeText(sign_in_screen.this,"Enter Login Details, or press again to exit", Toast.LENGTH_LONG).show();
        counter++;
        if (counter==2)
            super.onBackPressed();
            finish();
    }
}