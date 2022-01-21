package com.example.mmm_tp3;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RepositoryROOM implements IRepository{
    private com.example.mmm_tp3.UserDao userDao;
    private LiveData<List<User>> allUsers;

    public RepositoryROOM(Application application) {
        com.example.mmm_tp3.AppDataBase database = com.example.mmm_tp3.AppDataBase.getInstance(application);
        userDao = database.userDao();
        allUsers = userDao.getAll();
    }

    @Override
    public void insert(User note) {
        new InsertUserAsyncTask(userDao).execute(note);
    }

    @Override
    public void delete(User note) {
        new DeleteUserAsyncTask(userDao).execute(note);
    }

    @Override
    public void deleteAllUsers() {
        new DeleteAllUsersAsyncTask(userDao).execute();
    }

    @Override
    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private com.example.mmm_tp3.UserDao userDao;

        private InsertUserAsyncTask(com.example.mmm_tp3.UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }


    private static class DeleteUserAsyncTask extends AsyncTask<User, Void, Void> {
        private com.example.mmm_tp3.UserDao userDao;

        private DeleteUserAsyncTask(com.example.mmm_tp3.UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.delete(users[0]);
            return null;
        }
    }

    private static class DeleteAllUsersAsyncTask extends AsyncTask<Void, Void, Void> {
        private com.example.mmm_tp3.UserDao noteDao;

        private DeleteAllUsersAsyncTask(com.example.mmm_tp3.UserDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAll();
            return null;
        }
    }
}