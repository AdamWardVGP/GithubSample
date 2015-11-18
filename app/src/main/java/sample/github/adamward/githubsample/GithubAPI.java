package sample.github.adamward.githubsample;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface GithubAPI {
    @GET("/repos/{owner}/{repo}/issues")
    Observable<List<GithubIssue>> listIssues(@Path("owner") String user,
                                             @Path("repo") String repo);
}
