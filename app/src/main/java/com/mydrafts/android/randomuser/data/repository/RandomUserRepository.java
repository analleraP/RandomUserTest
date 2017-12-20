package com.mydrafts.android.randomuser.data.repository;

import com.mydrafts.android.randomuser.data.entity.ListMapper;
import com.mydrafts.android.randomuser.data.entity.UserMapper;
import com.mydrafts.android.randomuser.data.entity.apimodel.ApiUser;
import com.mydrafts.android.randomuser.data.entity.apimodel.PagedResult;
import com.mydrafts.android.randomuser.data.entity.User;
import com.mydrafts.android.randomuser.data.exception.NetworkErrorException;
import com.mydrafts.android.randomuser.data.exception.UnknownErrorException;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RandomUserRepository {

    private static final int RESULTS_AMOUNT = 10;

    private final RemoteDataSource remoteDataSource;
    private final LocalDataSource localDataSource;
    private final UserMapper userMapper;

    private int actualPage = 1;

    private final List<User> users;

    @Inject
    public RandomUserRepository(RemoteDataSource remoteDataSource, LocalDataSource localDataSource, UserMapper userMapper) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
        this.userMapper = userMapper;

        users = new ArrayList<>();
    }

    public List<User> getUsers() throws UnknownErrorException, NetworkErrorException {

        PagedResult<ApiUser> response = remoteDataSource.getApiUsers(actualPage, RESULTS_AMOUNT);

        List<ApiUser> apiUsers = response.getResults();

        ListMapper<User, ApiUser> userListMapper = new ListMapper<>(userMapper);
        List<User> newUsers = userListMapper.apiToModel(apiUsers);

        if (newUsers != null && newUsers.size() > 0) {
            actualPage++;

            //Delete duplicates
            newUsers.removeAll(users);

            //Delete blacklisted
            List<User> blacklisted = getBlacklisted();
            if (blacklisted != null) {
                newUsers.removeAll(blacklisted);
            }

            localDataSource.storeUsers(newUsers);
        }
        return newUsers;
    }

    public void blacklistUser(User user) {
        localDataSource.blacklistUser(user);
    }

    public User getUser(String userId) {
        return localDataSource.getUser(userId);
    }

    private List<User> getBlacklisted() {
        return localDataSource.getBlacklistedUsers();
    }
}
