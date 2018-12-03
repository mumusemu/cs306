package com.example.selin.databaseapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class addItem extends AppCompatActivity {

    Activity self;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        self = this;


        dbHelper = new DatabaseHelper(self);


        final TextView itemName = (TextView) findViewById(R.id.itemName);
        Button addBtn = (Button) findViewById(R.id.addBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Item  item = new Item();
                item.setItemName(itemName.getText().toString());
                item.setContact(currentUser.id);

                String message = "";

                if(dbHelper.insertItem(item)){
                    message = "Item Added, You can add new one";
                    itemName.setText("");
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
