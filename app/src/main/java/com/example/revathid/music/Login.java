package com.example.revathid.music;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener{
    Button bLogin;
    EditText eUsername, ePassword;
    TextView tRegisterLink;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);
        eUsername = (EditText) findViewById(R.id.eUsername);
        ePassword = (EditText) findViewById(R.id.ePassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        tRegisterLink = (TextView) findViewById(R.id.tRegisterLink);
        bLogin.setOnClickListener( this);
        tRegisterLink.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        String username = eUsername.getText().toString();
        String password = ePassword.getText().toString();
        Boolean checkunpw = db.match(username, password);

        switch (v.getId()) {
            case R.id.bLogin:
                if (checkunpw == true) {
                    Toast.makeText(getApplicationContext(), "Successfully Logged in", Toast.LENGTH_SHORT).show();
                    Toast.makeText(Login.this, "Welcome to Music Player ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, List.class));


                }
                else{
                    Toast.makeText(getApplicationContext(), "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tRegisterLink:
                startActivity(new Intent(this, Register.class));
                break;
        }
    }
}

