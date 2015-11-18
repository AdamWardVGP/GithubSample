package sample.github.adamward.githubsample.Fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.RestAdapter;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sample.github.adamward.githubsample.GithubAPI;
import sample.github.adamward.githubsample.GithubIssue;
import sample.github.adamward.githubsample.GithubIssueAdapter;
import sample.github.adamward.githubsample.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GithubIssuesFragment extends BaseFragment {


    @Bind(R.id.github_issues_list) ListView mResultList;


    private ArrayList<GithubIssue> mIssues;
    private WeakReference<GithubAPI> githubApi;
    private GithubIssueAdapter issueAdapter;
    private Subscription mSubscription;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        githubApi = new WeakReference<GithubAPI>(createGithubApi());
        mIssues = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_github_issues, container, false);

        ButterKnife.bind(this, view);

        issueAdapter = new GithubIssueAdapter(this.getActivity(),mIssues);
        mResultList.setAdapter(issueAdapter);

        mSubscription = githubApi.get().listIssues("rails","rails")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<GithubIssue>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<GithubIssue> githubIssues) {
                        mIssues.clear();
                        mIssues.addAll(githubIssues);
                        issueAdapter.notifyDataSetChanged();
                    }
                });

        return view;
    }


    @Override
    public void onPause() {
        super.onPause();
        if(mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }

    private GithubAPI createGithubApi() {

        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint("https://api.github.com/");
        //.setLogLevel(RestAdapter.LogLevel.FULL);

//        final String githubToken = getResources().getString(R.string.github_oauth_token);
//        if (!isEmpty(githubToken)) {
//            builder.setRequestInterceptor(new RequestInterceptor() {
//                @Override
//                public void intercept(RequestFacade request) {
//                    request.addHeader("Authorization", format("token %s", githubToken));
//                }
//            });
//        }

        return builder.build().create(GithubAPI.class);
    }

}
