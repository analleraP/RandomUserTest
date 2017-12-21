package com.mydrafts.android.randomuser;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mydrafts.android.randomuser.data.entity.User;
import com.mydrafts.android.randomuser.data.repository.RandomUserRepository;
import com.mydrafts.android.randomuser.di.AppComponent;
import com.mydrafts.android.randomuser.di.AppModule;
import com.mydrafts.android.randomuser.presentation.view.UserDetailsActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import it.cosenonjaviste.daggermock.DaggerMockRule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UserDetailsActivityTests {

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
    public ActivityTestRule<UserDetailsActivity> activityRule =
            new ActivityTestRule<>(UserDetailsActivity.class, true, false);

    @Mock
    RandomUserRepository repository;

    @Test
    public void showsUserEmail() {
        User user = givenAUser();

        startActivity(user);

        onView(withText(user.getEmail())).check(matches(isDisplayed()));
    }

    private User givenAUser() {

        String id = "idmd5-id";
        User user = new User(id);
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setGender("female");
        user.setEmail("jane@doe.com");

        when(repository.getUser(id)).thenReturn(user);
        return user;
    }

    private void startActivity(User user) {
        Intent intent = new Intent();
        intent.putExtra("user_id_key", user.getId());

        activityRule.launchActivity(intent);
    }
}
