package com.example.selin.databaseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.selin.databaseapp.R.*;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        helper = new DatabaseHelper(this);

    }

    public void onButtonClick(View v) {
        if (v.getId() == id.Blogin) {
            EditText a = (EditText) findViewById(id.TFusername);
            String str = a.getText().toString();
            EditText b = (EditText) findViewById(id.TFpassword);
            String pass = b.getText().toString();

            if (helper.login(str, pass)) {
                Intent i = new Intent(MainActivity.this, Display.class);
                i.putExtra("Username", str);
                startActivity(i);
            } else {
                //popup message
                Toast temp = Toast.makeText(MainActivity.this, "Username and Password don't match!", Toast.LENGTH_SHORT);
                temp.show();
            }

        }
        if (v.getId() == R.id.Bsignup) {
            Intent i = new Intent(MainActivity.this, SignUp.class);
            startActivity(i);
        }
    }
}

