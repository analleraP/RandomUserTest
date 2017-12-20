package com.mydrafts.android.randomuser;

import com.mydrafts.android.randomuser.data.entity.apimodel.ApiUser;
import com.mydrafts.android.randomuser.data.entity.apimodel.PagedResult;
import com.mydrafts.android.randomuser.data.exception.NetworkErrorException;
import com.mydrafts.android.randomuser.data.exception.UnknownErrorException;
import com.mydrafts.android.randomuser.data.repository.RemoteDataSource;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoteDataSourceTest {

    private RemoteDataSource remoteDataSource;

    @Before
    public void setUp() {
        remoteDataSource = new RemoteDataSource();
    }

    @Test
    public void testGetUserEntityListFromRemote() throws NetworkErrorException, UnknownErrorException {

        PagedResult<ApiUser> response = remoteDataSource.getApiUsers(30, 20);

        assertThat(response.getResults().size(), is(20));
        assertThat(response.getInfo().getPage(), is(30));
        assertThat(response.getResults().get(3), is(instanceOf(ApiUser.class)));
    }
}