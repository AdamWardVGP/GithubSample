package sample.github.adamward.githubsample;

import android.support.test.InstrumentationRegistry;

/**
 * Created by adamward on 11/18/15.
 */
public class Util {
    public static String getString(int resID) {
        return InstrumentationRegistry.getTargetContext().getString(resID);
    }
}
