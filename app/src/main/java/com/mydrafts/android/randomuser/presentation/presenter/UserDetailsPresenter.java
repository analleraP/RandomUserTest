package com.mydrafts.android.randomuser.presentation.presenter;

import com.mydrafts.android.randomuser.data.entity.User;
import com.mydrafts.android.randomuser.di.ActivityScope;
import com.mydrafts.android.randomuser.domain.GetUserUseCase;
import com.mydrafts.android.randomuser.domain.UseCaseCallback;

import javax.inject.Inject;

@ActivityScope
public class UserDetailsPresenter extends Presenter<UserDetailsPresenter.View> {

    private final GetUserUseCase getUserUseCase;

    @Inject
    public UserDetailsPresenter(GetUserUseCase getUserUseCase) {
        this.getUserUseCase = getUserUseCase;
    }

    public void onLoadUserByID(String id) {
        getUserUseCase.getUser(id, new UseCaseCallback<User>() {
            @Override
            public void onSuccess(User user) {
                View view = getView();
                view.showUserDetails(user);
            }

            @Override
            public void onError(Error error) {
                getView().showErrorView(error.message());
            }
        });
    }

    public interface View extends Presenter.View {

        void showErrorView(String message);

        void showUserDetails(User user);
    }
}
