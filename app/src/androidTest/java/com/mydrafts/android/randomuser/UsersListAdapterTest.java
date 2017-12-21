package com.mydrafts.android.randomuser;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.mydrafts.android.randomuser.data.entity.User;
import com.mydrafts.android.randomuser.di.AppComponent;
import com.mydrafts.android.randomuser.di.AppModule;
import com.mydrafts.android.randomuser.presentation.presenter.UsersListPresenter;
import com.mydrafts.android.randomuser.presentation.view.adapter.UsersListAdapter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import it.cosenonjaviste.daggermock.DaggerMockRule;

@RunWith(AndroidJUnit4.class)
public class UsersListAdapterTest {

    @Mock
    protected RecyclerView recyclerView;

    @Mock
    protected UsersListPresenter presenter;

    protected UsersListAdapter adapter;

    @Before
    public void setUp() {

        adapter = new UsersListAdapter(presenter, recyclerView);
    }

    @Test
    public void testAddUsers() {
        adapter.addUsers(getUserList());
        int prevSize = adapter.getItemCount();

        List<User> moreUsers = getAdditionalUserList();
        int expectedSize = adapter.getItemCount() + moreUsers.size();

        adapter.addUsers(moreUsers);
        int result = adapter.getItemCount();

        Assert.assertTrue(expectedSize == result);
        Assert.assertTrue(prevSize <= result);
    }

    @Test
    public void testRemoveUsers() {
        adapter.addUsers(getUserList());

        int prevSize = adapter.getItemCount();
        int expectedSize = prevSize - 1;

        adapter.deleteRow(1);

        int result = this.adapter.getItemCount();

        Assert.assertTrue(expectedSize == result);
        Assert.assertTrue(prevSize >= result);
    }

    private User fakeUser1() {
        return new User("fakeid1");
    }

    private User fakeUser2() {
        return new User("fakeid2");
    }

    private List<User> getUserList() {
        List<User> users = new ArrayList<User>();

        users.add(fakeUser1());
        users.add(fakeUser2());

        return users;
    }

    private List<User> getAdditionalUserList() {
        List<User> temp = new ArrayList<User>();
        User fakeUser3 = new User("fakeid3");
        temp.add(fakeUser3);

        return temp;
    }
}