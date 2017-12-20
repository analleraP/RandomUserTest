package com.mydrafts.android.randomuser.data.repository;

import android.support.annotation.NonNull;

import com.mydrafts.android.randomuser.data.db.UserDao;
import com.mydrafts.android.randomuser.data.entity.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class LocalDataSource {

    private UserDao userDao;

    @Inject
    public LocalDataSource(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getBlacklistedUsers() {
        return userDao.getBlacklistedUsers();
    }

    public void storeUsers(List<User> users) {
        userDao.insertUsers(users);
    }

    public void blacklistUser(@NonNull User user) {
        user.setBlacklisted(true);
        userDao.updateUser(user);
    }

    public User getUser(@NonNull String id) {
        return userDao.getUser(id);
    }
}
