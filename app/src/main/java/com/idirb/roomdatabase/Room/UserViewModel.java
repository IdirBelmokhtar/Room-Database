package com.idirb.roomdatabase.Room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository mRepository;

    private LiveData<List<User>> mAllUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        mRepository = new UserRepository(application);
        mAllUsers = mRepository.getGetAllUsers();
    }

    public void insert(User user) {
        mRepository.insert(user);
    }

    public void delete(User user) {
        mRepository.delete(user);
    }

    public void update(User user) {
        mRepository.update(user);
    }

    public void deleteAllUsers() {
        mRepository.deleteAllUser();
    }

    public LiveData<List<User>> getAllUsers() {
        return mAllUsers;
    }
}
