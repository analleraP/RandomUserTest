package com.mydrafts.android.randomuser;

import android.app.Application;
import android.content.Context;
import android.support.annotation.VisibleForTesting;

import com.mydrafts.android.randomuser.di.AppComponent;
import com.mydrafts.android.randomuser.di.AppModule;
import com.mydrafts.android.randomuser.di.DaggerAppComponent;

public class RandomUserApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public static RandomUserApplication get(Context context) {
        return (RandomUserApplication) context.getApplicationContext();
    }

    @VisibleForTesting
    public void setComponent(AppComponent appComponent) {
        appComponent = appComponent;
    }

}
