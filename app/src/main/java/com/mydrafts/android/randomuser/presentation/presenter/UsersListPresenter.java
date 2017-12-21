package com.mydrafts.android.randomuser.presentation.presenter;

import com.mydrafts.android.randomuser.data.entity.User;
import com.mydrafts.android.randomuser.di.ActivityScope;
import com.mydrafts.android.randomuser.domain.GetUsersUseCase;
import com.mydrafts.android.randomuser.domain.UseCaseCallback;

import java.util.List;

import javax.inject.Inject;

@ActivityScope
public class UsersListPresenter extends Presenter<UsersListPresenter.View> {

    private final GetUsersUseCase getUsersUseCase;

    @Inject
    public UsersListPresenter(GetUsersUseCase getUsersUseCase) {
        this.getUsersUseCase = getUsersUseCase;
    }

    public void onLoadInitialUsers() {
        getUsersUseCase.getUsers(new UseCaseCallback<List<User>>() {
            @Override
            public void onSuccess(List<User> users) {
                View view = getView();

                if (users.isEmpty()) {
                    view.showEmptyView();
                } else {
                    view.hideEmptyView();
                    view.showUsersList(users);
                }
            }

            @Override
            public void onError(Error error) {
                getView().showErrorView(error.message());
            }
        });
    }

    public interface View extends Presenter.View {

        void showEmptyView();

        void hideEmptyView();

        void showErrorView(String message);

        void showUsersList(List<User> users);
    }
}
