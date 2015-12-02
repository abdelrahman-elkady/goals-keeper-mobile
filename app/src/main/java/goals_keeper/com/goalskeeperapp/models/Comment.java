package goals_keeper.com.goalskeeperapp.models;

/**
 * Created by ali on 2/12/15.
 */
public class Comment {
    private User user;
    private Post post;
    private String body;

    public Comment (User user, Post post, String body)
    {
        this.user = user;
        this.post = post;
        this.body = body;
    }
}
