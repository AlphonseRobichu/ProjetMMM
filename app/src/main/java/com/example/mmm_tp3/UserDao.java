package com.example.mmm_tp3;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user_table ")
    LiveData<List<User>> getAll();

    @Query("SELECT * FROM user_table WHERE uid IN (:userIds)")
    LiveData<List<User>> loadAllByIds(int[] userIds);


    @Query("SELECT * FROM user_table WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);

    @Insert
    void insertAll(User... users);

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);

    @Delete
    void deleteAll(User... users);

}


