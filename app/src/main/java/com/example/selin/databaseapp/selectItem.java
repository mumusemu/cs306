package com.example.selin.databaseapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class selectItem extends AppCompatActivity {

    Activity self;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_item);

        self = this;

        dbHelper = new DatabaseHelper(self);

        ListView lv = (ListView) findViewById(R.id.listView);

        dbHelper.getItemsOfContact(self,currentUser.id,lv);

    }
}
