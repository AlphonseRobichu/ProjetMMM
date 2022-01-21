package com.example.mmm_tp3;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModel extends AndroidViewModel {
    private IRepository repository;
    private LiveData<List<User>> allUsers;

    public ViewModel(@NonNull Application application) {
        super(application);
        //repository = new RepositoryROOM(application);
        repository = new RepositoryFirestore();
        allUsers = repository.getAllUsers();
    }

    public void insert(User user) {
        repository.insert(user);
    }

    public void delete(User user) {
        repository.delete(user);
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }
}


