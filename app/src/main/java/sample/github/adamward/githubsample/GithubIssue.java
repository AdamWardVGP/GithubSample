package sample.github.adamward.githubsample;

final public class GithubIssue {
    public final int id;
    public final String url;
    public final String comments_url;
    public final int number;
    public final String title;
    public final String body;

    public GithubIssue(int id, String url, String comments_url, int number, String title, String body) {
        this.id = id;
        this.url = url;
        this.comments_url = comments_url;
        this.number = number;
        this.title = title;
        this.body = body;
    }
}
