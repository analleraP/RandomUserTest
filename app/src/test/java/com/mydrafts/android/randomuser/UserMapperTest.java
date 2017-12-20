package com.mydrafts.android.randomuser;

import com.mydrafts.android.randomuser.data.entity.ListMapper;
import com.mydrafts.android.randomuser.data.entity.User;
import com.mydrafts.android.randomuser.data.entity.UserMapper;
import com.mydrafts.android.randomuser.data.entity.apimodel.ApiUser;
import com.mydrafts.android.randomuser.data.entity.apimodel.Location;
import com.mydrafts.android.randomuser.data.entity.apimodel.Name;
import com.mydrafts.android.randomuser.data.entity.apimodel.Picture;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserMapperTest {

    private UserMapper userMapper;
    private ListMapper<User, ApiUser> userListMapper;

    @Before
    public void setUp() throws Exception {
        userMapper = new UserMapper();
        userListMapper = new ListMapper<>(userMapper);
    }

    @Test
    public void testMapToUserEntity() {
        ApiUser fakeApiUser = createFakeApiUser1();
        User user = userMapper.apiToModel(fakeApiUser);

        assertThat(user, is(instanceOf(User.class)));
        assertThat(user.getEmail(), is("jane@doe.com"));
        assertThat(user.getFullName(), is("Jane Doe"));
    }

    @Test
    public void testMapToEntityCollection() {
        ApiUser fakeApiUser1 = createFakeApiUser1();
        ApiUser fakeApiUser2 = createFakeApiUser2();

        List<ApiUser> apiUsersList = new ArrayList<>(5);
        apiUsersList.add(fakeApiUser1);
        apiUsersList.add(fakeApiUser2);

        List<User> users = userListMapper.apiToModel(apiUsersList);

        assertThat(users.toArray()[0], is(instanceOf(User.class)));
        assertThat(users.toArray()[1], is(instanceOf(User.class)));
        assertThat(users.size(), is(2));
    }

    private ApiUser createFakeApiUser1() {

        return new ApiUser(
                "female",
                new Name("mrs", "Jane", "Doe"),
                new Location("that street", "in the middle of nowhere", "from no place to hide"),
                "jane@doe.com",
                "",
                "(666)-666-6666",
                new Picture("", "", ""));
    }

    private ApiUser createFakeApiUser2() {

        return new ApiUser(
                "male",
                new Name("mr", "John", "Doe"),
                new Location("that street", "in the middle of nowhere", "from no place to hide"),
                "john@doe.com",
                "",
                "(999)-999-9999",
                new Picture("", "", ""));
    }
}
