package com.idirb.roomdatabase.Room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {

    private UserDao mUserDao;
    private LiveData<List<User>> getAllUsers;

    public UserRepository(Application app) {
        UserRoomDB db = UserRoomDB.getInstance(app);
        mUserDao = db.userDao();
        getAllUsers = mUserDao.getAllUsers();
    }

    /** Operation */

    // Insert
    public void insert(User user) {
        new InsertAsyncTask(mUserDao).execute(user);
    }

    // Delete
    public void delete(User user) {
        new DeleteAsyncTask(mUserDao).execute(user);
    }

    // Update
    public void update(User user) {
        new UpdateAsyncTask(mUserDao).execute(user);
    }

    // Get all users
    public LiveData<List<User>> getGetAllUsers() {
        return getAllUsers;
    }

    // Delete all users
    public void deleteAllUser() {
        new DeleteAllUsersAsyncTask(mUserDao).execute();
    }


    /** AsyncTasks (Threads) */

    private static class InsertAsyncTask extends AsyncTask<User, Void, Void>{

        private UserDao mUserDao;

        public InsertAsyncTask(UserDao mUserDao) {
            this.mUserDao = mUserDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            mUserDao.insert(users[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<User, Void, Void>{

        private UserDao mUserDao;

        public DeleteAsyncTask(UserDao mUserDao) {
            this.mUserDao = mUserDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            mUserDao.delete(users[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<User, Void, Void>{

        private UserDao mUserDao;

        public UpdateAsyncTask(UserDao mUserDao) {
            this.mUserDao = mUserDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            mUserDao.update(users[0]);
            return null;
        }
    }

    private static class DeleteAllUsersAsyncTask extends AsyncTask<Void, Void, Void>{

        private UserDao mUserDao;

        public DeleteAllUsersAsyncTask(UserDao mUserDao) {
            this.mUserDao = mUserDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mUserDao.deleteAllUsers();
            return null;
        }
    }
}
