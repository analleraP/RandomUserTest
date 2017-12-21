package com.mydrafts.android.randomuser.di;

import android.app.Application;

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
}