package com.mydrafts.android.randomuser.domain;

import android.os.Handler;
import android.os.Looper;

import com.mydrafts.android.randomuser.data.entity.User;
import com.mydrafts.android.randomuser.data.exception.NetworkErrorException;
import com.mydrafts.android.randomuser.data.exception.UnknownErrorException;
import com.mydrafts.android.randomuser.data.repository.RandomUserRepository;

import java.util.List;

import javax.inject.Inject;

public class GetUsersUseCase {

    private final RandomUserRepository randomUserRepository;

    @Inject
    public GetUsersUseCase(RandomUserRepository randomUserRepository) {
        this.randomUserRepository = randomUserRepository;
    }

    public void getUsers(final UseCaseCallback<List<User>> callback) {

        new Thread(new Runnable() {
            @Override public void run() {
                loadUsers(callback);
            }
        }).start();
    }

    private void loadUsers(final UseCaseCallback<List<User>> callback) {
        try {
            final List<User> users = randomUserRepository.getUsers();

            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override public void run() {
                    callback.onSuccess(users);
                }
            });
        } catch (NetworkErrorException e) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override public void run() {
                    callback.onError(UseCaseCallback.Error.NETWORK);
                }
            });
        } catch (Exception e) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override public void run() {
                    callback.onError(UseCaseCallback.Error.UNKNOWN);
                }
            });
        }
    }


}
