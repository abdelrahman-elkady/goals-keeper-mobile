package goals_keeper.com.goalskeeperapp.models;

import java.util.ArrayList;

/**
 * Created by kady on 01/12/15.
 *
 * @author kady
 */
public class Post {
    // TODO: add user model !
    private String content;
    private User user;
    private boolean isPrivate;

    private ArrayList<Like> likes;
    private ArrayList<Comment> comments;
    public Post(User user, String content) {
        this.user = user;
        this.content = content;
        this.likes = new ArrayList<Like>();
        this.comments = new ArrayList<Comment>();
        isPrivate = false;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
