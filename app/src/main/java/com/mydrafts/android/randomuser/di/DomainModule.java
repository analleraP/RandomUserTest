package com.mydrafts.android.randomuser.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.mydrafts.android.randomuser.data.db.UserDao;
import com.mydrafts.android.randomuser.data.db.UserDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DomainModule {

    @Provides
    @Singleton
    public UserDatabase provideUserDb(Application application) {
        return Room.databaseBuilder(application, UserDatabase.class, "user.db").build();
    }

    @Provides
    @Singleton
    public UserDao provideUserDao(UserDatabase userDatabase) {
        return userDatabase.userDao();
    }
}
