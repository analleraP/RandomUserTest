package com.mydrafts.android.randomuser.domain;

import android.os.Handler;
import android.os.Looper;

import com.mydrafts.android.randomuser.data.entity.User;
import com.mydrafts.android.randomuser.data.repository.RandomUserRepository;

import javax.inject.Inject;

public class BlacklistUserUseCase {

    private final RandomUserRepository randomUserRepository;

    @Inject
    public BlacklistUserUseCase(RandomUserRepository randomUserRepository) {
        this.randomUserRepository = randomUserRepository;
    }

    public void blacklistUser(final User user, final UseCaseCallback<User> callback) {

        new Thread(new Runnable() {
            @Override public void run() {
                try {
                    randomUserRepository.blacklistUser(user);

                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override public void run() {
                            callback.onSuccess(user);
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
        }).start();
    }
}
