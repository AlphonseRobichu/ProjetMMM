package com.example.mmm_tp3;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface IRepository {
    void insert(User user);
    void delete(User user);
    void deleteAllUsers();
    LiveData<List<User>> getAllUsers();
}
