package com.example.mmm_tp3;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    public int getUid() {
        return uid;
    }

    //Used by firebase
    public void setUid(int uid) {
        this.uid = uid;
    }

    public User(){

    }

    public User(String firstName, String lastName) {
        this.lastName = firstName;
        this.firstName = lastName;
    }

}
