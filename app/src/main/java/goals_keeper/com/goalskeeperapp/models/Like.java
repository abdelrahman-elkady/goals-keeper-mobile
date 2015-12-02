package goals_keeper.com.goalskeeperapp.models;


public class Like {
    private User user;
    private Post post;

    public Like (User user, Post post)
    {
        this.user = user;
        this.post = post;
    }
}
