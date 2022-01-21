package com.example.mmm_tp3;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {User.class}, version = 2)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase instance;

    public abstract UserDao userDao();

    public static synchronized AppDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDataBase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }


    private static Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDao noteDao;

        private PopulateDbAsyncTask(AppDataBase db) {
            noteDao = db.userDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            User u1 = new User("Ada","Lovelace");
            User u2 = new User("Charles","Babbage");

            noteDao.insert(u1);
            noteDao.insert(u2);
            noteDao.insert(u1);
            noteDao.insert(u2);


            return null;
        }
    }

}
