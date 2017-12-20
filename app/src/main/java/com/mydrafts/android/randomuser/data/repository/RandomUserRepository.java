package com.mydrafts.android.randomuser.data.repository;

import com.mydrafts.android.randomuser.data.entity.PagedResult;
import com.mydrafts.android.randomuser.data.entity.User;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class RandomUserRepository implements Repository<User> {

    private static final int RESULTS_AMOUNT = 10;

    private final RemoteDataSource remoteDataSource;

    private int actualPage;

    private final List<User> users;

    public RandomUserRepository(RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;

        actualPage = 1;
        users = new ArrayList<>();
    }

    @Override
    public Observable<List<User>> getUsers() {

        return remoteDataSource.getUsers(actualPage, RESULTS_AMOUNT)
                .flatMap(new Function<PagedResult<User>, Observable<List<User>>>() {
                    @Override
                    public Observable<List<User>> apply(PagedResult<User> userPagedResult) throws Exception {

                        actualPage++;

                        return Observable.just(userPagedResult.getResults());
                    }
                })
                .flatMap(new Function<List<User>, Observable<List<User>>>() {
                    @Override
                    public Observable<List<User>> apply(List<User> newUsers) throws Exception {

                        newUsers.removeAll(users);

                        users.addAll(newUsers);
                        return Observable.just(newUsers);
                    }
                });
    }

    public User getUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
}
