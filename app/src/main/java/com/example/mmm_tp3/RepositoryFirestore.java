package com.example.mmm_tp3;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.room.Database;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class RepositoryFirestore implements IRepository{

    private DatabaseReference db;
    private MutableLiveData<List<User>> liveData;

    public MutableLiveData<List<User>> getLiveData() {
        if (liveData == null){
            liveData = new MutableLiveData<>();
        }
        return liveData;
    }

    public RepositoryFirestore(){
        db = FirebaseDatabase.getInstance().getReference();
        ValueEventListener postListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<User> users = new ArrayList<>();
                for(DataSnapshot user : dataSnapshot.getChildren()){
                    users.add(user.getValue(User.class));
                }
                getLiveData().setValue(users);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG-ERROR-CANCEL", "cancel", databaseError.toException());
            }
        };
        db.child("users").addValueEventListener(postListener);
    }

    @Override
    public void insert(User user) {
        db.child("users").child(user.firstName + user.lastName).setValue(user);
    }

    @Override
    public void delete(User user) {
        db.child("users").child(user.firstName + user.lastName).removeValue();
    }

    @Override
    public void deleteAllUsers() {
        db.child("users").removeValue();
    }

    @Override
    public LiveData<List<User>> getAllUsers() {
        return getLiveData();
    }
}
