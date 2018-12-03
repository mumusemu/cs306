package com.example.selin.databaseapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Selin on 13.05.2016.
 */
public class Display extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);
        String username = getIntent().getStringExtra("Username");

        TextView tv = (TextView) findViewById(R.id.TVusername);
        tv.setText(username);

        Button addBtn = (Button) findViewById(R.id.Uadd);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Display.this, addItem.class);
                startActivity(i);
            }
        });


        Button selBtn = (Button) findViewById(R.id.Uselect);
        selBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Display.this, selectItem.class);
                startActivity(i);
            }
        });


        Button delBtn = (Button) findViewById(R.id.Udelete);
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Display.this, deleteItem.class);
                startActivity(i);
            }
        });

    }

}
