package com.example.selin.databaseapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class deleteItem extends AppCompatActivity {


    Activity self;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_item);

        self = this;

        dbHelper = new DatabaseHelper(self);

        final EditText itemId = (EditText) findViewById(R.id.itemId);

        Button delBtn = (Button) findViewById(R.id.delBtn);

        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "";

                if (dbHelper.deleteItem(Integer.parseInt(itemId.getText().toString()))) {
                    message = "Item deleted";
                } else {
                    message = "An error occured";
                }

                Toast temp = Toast.makeText(self, message, Toast.LENGTH_SHORT);
                temp.show();
            }
        });


        Button quit = (Button) findViewById(R.id.quit);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
