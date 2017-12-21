package com.mydrafts.android.randomuser.di;

import android.app.Application;

import com.mydrafts.android.randomuser.data.db.UserDao;
import com.mydrafts.android.randomuser.data.entity.UserMapper;
import com.mydrafts.android.randomuser.data.repository.LocalDataSource;
import com.mydrafts.android.randomuser.data.repository.RandomUserRepository;
import com.mydrafts.android.randomuser.data.repository.RemoteDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class AppModule {

    private final Application context;

    public AppModule(Application context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Application provideApplicationContext() {
        return context;
    }

    @Provides
    @Singleton
    public RemoteDataSource provideRemoteDataSource() {
        return new RemoteDataSource();
    }

    @Provides
    @Singleton
    public LocalDataSource provideLocalDataSource(UserDao userDao) {
        return new LocalDataSource(userDao);
    }

    @Provides
    @Singleton
    public RandomUserRepository provideRandomUserRepository(RemoteDataSource remoteDataSource,
                                                            LocalDataSource localDataSource,
                                                            UserMapper userMapper) {
        return new RandomUserRepository(remoteDataSource, localDataSource, userMapper);
    }

}