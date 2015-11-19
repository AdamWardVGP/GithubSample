package sample.github.adamward.githubsample;

import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

//*******STATIC IMPORTS*********
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static sample.github.adamward.githubsample.CustomMatchers.isDisabled;
import static sample.github.adamward.githubsample.Util.getString;

/**
 * Created by adamward on 11/18/15.
 */
public class MainActivityInstrumentationTest {

    // Preferred JUnit 4 mechanism of specifying the activity to be launched before each test
    @Rule
    public ActivityTestRule<MainAppActivity> activityTestRule =
            new ActivityTestRule<>(MainAppActivity.class);

    @Test
    public void validateWelcomeMessage() {
        onView(withId(R.id.welcome_message)).check(matches(withText(getString(R.string.welcome_message))));
    }

    @Test
    public void testErrorTextFields() {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withText(getString(R.string.menu_forum_error_check))).perform(click());
        onView(withId(R.id.name_edit_text)).perform(typeText("myname")).check(matches(withText("myname")));
        onView(withId(R.id.submit_forum_btn)).check(matches(isDisabled()));
        onView(withId(R.id.email_edit_text)).perform(typeText("example@test.com"));
        onView(withId(R.id.submit_forum_btn)).check(matches(isDisabled()));
        onView(withId(R.id.pin_edit_text)).perform(typeText("12345"));
        onView(withId(R.id.submit_forum_btn)).check(matches(isEnabled()));
    }
}