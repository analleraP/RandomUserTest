package com.mydrafts.android.randomuser;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.mydrafts.android.randomuser.data.entity.User;
import com.mydrafts.android.randomuser.data.exception.NetworkErrorException;
import com.mydrafts.android.randomuser.data.exception.UnknownErrorException;
import com.mydrafts.android.randomuser.data.repository.RandomUserRepository;
import com.mydrafts.android.randomuser.di.AppComponent;
import com.mydrafts.android.randomuser.di.AppModule;
import com.mydrafts.android.randomuser.presentation.view.UserDetailsActivity;
import com.mydrafts.android.randomuser.presentation.view.UsersListActivity;
import com.mydrafts.android.randomuser.recyclerview.RecyclerViewInteraction;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.cosenonjaviste.daggermock.DaggerMockRule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UsersListActivityTest {

    private static final int NUMBER_OF_USERS = 10;

    @Rule
    public DaggerMockRule<AppComponent> daggerRule = new DaggerMockRule<>(AppComponent.class,
            new AppModule((RandomUserApplication) InstrumentationRegistry.getInstrumentation()
                    .getTargetContext()
                    .getApplicationContext())).set(new DaggerMockRule.ComponentSetter<AppComponent>() {
        @Override
        public void setComponent(AppComponent component) {
            RandomUserApplication app = (RandomUserApplication) InstrumentationRegistry.getInstrumentation()
                    .getTargetContext()
                    .getApplicationContext();
            app.setComponent(component);
        }
    });

    @Rule
    public IntentsTestRule<UsersListActivity> activityRule =
            new IntentsTestRule<>(UsersListActivity.class, true, false);

    @Mock
    private RandomUserRepository repository;

    @Test
    public void showsEmptyCaseIfThereAreNoUsers() throws UnknownErrorException, NetworkErrorException {
        givenThereAreNoUsers();

        startActivity();

        onView(withText("Sorry no random users for now :(")).check(matches(isDisplayed()));
    }

    @Test
    public void doesNotShowEmptyCaseIfThereAreUsers() throws UnknownErrorException, NetworkErrorException {
        givenThereAreSomeUsers(NUMBER_OF_USERS);

        startActivity();

        onView(withId(R.id.tv_empty)).check(matches(not(isDisplayed())));
    }

    @Test
    public void showsUsersNameIfThereAreUsers() throws UnknownErrorException, NetworkErrorException {
        List<User> users = givenThereAreSomeUsers(NUMBER_OF_USERS);

        startActivity();

        RecyclerViewInteraction.<User>onRecyclerView(withId(R.id.recycler_view))
                .withItems(users)
                .check(new RecyclerViewInteraction.ItemViewAssertion<User>() {
                    @Override
                    public void check(User user, View view, NoMatchingViewException e) {
                        matches(hasDescendant(withText(user.getFullName()))).check(view, e);
                    }
                });
    }

    @Test
    public void opensUserDetailsActivityOnRecyclerViewItemTapped() throws UnknownErrorException, NetworkErrorException {
        List<User> users = givenThereAreSomeUsers(NUMBER_OF_USERS);
        int userIndex = 0;
        startActivity();

        onView(withId(R.id.recycler_view)).
                perform(RecyclerViewActions.actionOnItemAtPosition(userIndex, click()));

        User userSelected = users.get(userIndex);
        intended(hasComponent(UserDetailsActivity.class.getCanonicalName()));
        intended(hasExtra("user_id_key", userSelected.getId()));
    }

    private void givenThereAreNoUsers() throws NetworkErrorException, UnknownErrorException {
        when(repository.getUsers()).thenReturn(Collections.<User>emptyList());
    }

    private void startActivity() {
        activityRule.launchActivity(null);
    }

    private List<User> givenThereAreSomeUsers(int numberOfUsers) throws NetworkErrorException, UnknownErrorException {
        List<User> users = new ArrayList<>();

        for (int i = 0; i < numberOfUsers; i++) {

            String id = "idmd5-" + i;
            User user = new User(id);
            user.setFirstName("FirstName" + i);
            user.setLastName("LastName" + i);
            user.setGender(i % 2 == 0 ? "female" :"male");
            user.setEmail("Email" + i);

            users.add(user);
            when(repository.getUser(id)).thenReturn(user);
        }
        when(repository.getUsers()).thenReturn(users);
        return users;
    }
}