package com.example.selin.databaseapp;

import android.content.ComponentName;

/**
 * Created by Selin on 15.05.2016.
 */
public class Item  {
    String name;
    int f_id;



    public void setContact(int fid){this.f_id=fid;}
    public int getContact(){return this.f_id;}

    public void setItemName(String name){this.name=name;}
    public String getItemName(){return this.name;}

}
