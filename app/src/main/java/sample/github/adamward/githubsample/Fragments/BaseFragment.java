package sample.github.adamward.githubsample.Fragments;

import android.support.v4.app.Fragment;
import com.squareup.leakcanary.RefWatcher;
import sample.github.adamward.githubsample.MyApplication;

/**
 * all fragments in the app should extend this fragment to allow tracking of memory leaks
 */
public class BaseFragment extends Fragment {

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = MyApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }
}
