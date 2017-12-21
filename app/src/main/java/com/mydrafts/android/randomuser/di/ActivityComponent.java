package com.mydrafts.android.randomuser.di;

import com.mydrafts.android.randomuser.presentation.view.UserDetailsActivity;
import com.mydrafts.android.randomuser.presentation.view.UsersListActivity;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(UsersListActivity activity);

    void inject(UserDetailsActivity activity);
}