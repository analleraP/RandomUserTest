package com.mydrafts.android.randomuser;

import com.mydrafts.android.randomuser.data.entity.User;
import com.mydrafts.android.randomuser.data.entity.UserMapper;
import com.mydrafts.android.randomuser.data.exception.NetworkErrorException;
import com.mydrafts.android.randomuser.data.exception.UnknownErrorException;
import com.mydrafts.android.randomuser.data.repository.LocalDataSource;
import com.mydrafts.android.randomuser.data.repository.RandomUserRepository;
import com.mydrafts.android.randomuser.data.repository.RemoteDataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.mockito.Mockito.mock;

@RunWith(JUnit4.class)
public class RandomUserRepositoryTest {

    private RemoteDataSource remoteDataSource;
    private LocalDataSource localDataSource;
    private UserMapper userMapper;

    RandomUserRepository repository;

    @Before
    public void setup() {
        remoteDataSource = mock(RemoteDataSource.class);
        localDataSource = mock(LocalDataSource.class);
        userMapper = mock(UserMapper.class);

        repository = new RandomUserRepository(remoteDataSource, localDataSource, userMapper);
    }

    @Test
    public void loadUsersTest() throws NetworkErrorException, UnknownErrorException {
        List<User> users = repository.getUsers();
        Assert.assertTrue(users.size() > 0);
    }
}