package com.example.memexi;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    EditText Name;

    EditText Password;
    Button   sign_in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);
        String name,phoneNo,password;
        Name = findViewById(R.id.editTextTextPersonName);

        Password = findViewById(R.id.password_toggle);
        name = String.valueOf(Name.getText());

        password = String.valueOf(Password.getText());
        sign_in = findViewById(R.id.Sign_In);
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name,phoneNo,password;
                Name = findViewById(R.id.editTextTextPersonName);
                Password = findViewById(R.id.password_toggle);
                name = String.valueOf(Name.getText());
                password = String.valueOf(Password.getText());
                if(name.length()>30){
                    Name.setText("");
                    Toast.makeText(MainActivity.this, "Name or email can't be greater than 30 character", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length()>20) {
                    Password.setText("");
                    Toast.makeText(MainActivity.this, "Password can't be greater than 20 character", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent in = new Intent(MainActivity.this,HomePage.class);
                in.putExtra("name",name);
                in.putExtra("password",password);

                startActivity(in);

            }
        });

    }


}