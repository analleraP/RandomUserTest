package com.mydrafts.android.randomuser.di;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { AppModule.class, DomainModule.class })
public interface AppComponent {

    ActivityComponent activityComponent(ActivityModule activityModule);
}