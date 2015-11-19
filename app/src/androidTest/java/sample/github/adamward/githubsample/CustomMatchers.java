package sample.github.adamward.githubsample;

import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;

/**
 * Created by adamward on 11/18/15.
 */
public class CustomMatchers {
    /**
     * Returns a matcher that matches {@link View}s that are disabled.
     */
    public static Matcher<View> isDisabled() {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("is disabled");
            }

            @Override
            public boolean matchesSafely(View view) {
                return !view.isEnabled();
            }
        };
    }
}
