package com.mydrafts.android.randomuser;

import com.mydrafts.android.randomuser.data.db.UserDao;
import com.mydrafts.android.randomuser.data.entity.User;
import com.mydrafts.android.randomuser.data.repository.LocalDataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class LocalDataSourceTest {

    private LocalDataSource localDataSource;

    @Mock
    protected UserDao userDao;


    @Before
    public void setUp() {
        localDataSource = new LocalDataSource(userDao);
    }

    @Test
    public void testBlacklistedUser() {

        User fakeUser1 = new User("janedoemd5");
        User fakeUser2 = new User("johndoemd5");

        localDataSource.blacklistUser(fakeUser1);

        Assert.assertTrue(fakeUser1.isBlacklisted());
        Assert.assertFalse(fakeUser2.isBlacklisted());
    }

    @Test
    public void testSaveUser() {

        User fakeUser1 = new User("janedoemd5");
        User fakeUser2 = new User("johndoemd5");

        List<User> fakeUsers = new ArrayList();
        fakeUsers.add(fakeUser1);
        fakeUsers.add(fakeUser2);

        localDataSource.storeUsers(fakeUsers);

        User response = localDataSource.getUser(fakeUser1.getId());

        Assert.assertTrue(response != null);
    }
}