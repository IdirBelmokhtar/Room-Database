package com.idirb.roomdatabase.Room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

//------------------{User.class, User2.class ...}
@Database(entities = User.class, version = 1)
public abstract class UserRoomDB extends RoomDatabase {

    private static UserRoomDB instance;

    public abstract UserDao userDao();

    // Singlton
    public static synchronized UserRoomDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            UserRoomDB.class, "user_database")
                    .fallbackToDestructiveMigration()
                    //.addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    // Callback for test
    /*private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDataAsyncTask(instance).execute();
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };

    private static class PopulateDataAsyncTask extends AsyncTask<Void, Void, Void>{

        private UserDao mUserDao;

        public PopulateDataAsyncTask(UserRoomDB db) {
            this.mUserDao = db.userDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mUserDao.insert(new User("idir", "belmokhtar", 22));
            mUserDao.insert(new User("idirIDIR", "belmokhtarBELMOKHTAR", 23));
            return null;
        }
    }*/

}
