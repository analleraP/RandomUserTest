package com.mydrafts.android.randomuser.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.mydrafts.android.randomuser.data.entity.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUsers(List<User> users);

    @Query("SELECT * FROM users WHERE id = :id")
    User getUser(String id);

    @Query("SELECT * FROM users WHERE isBlacklisted")
    List<User> getBlacklistedUsers();

    @Update
    void updateUser(User user);
}
