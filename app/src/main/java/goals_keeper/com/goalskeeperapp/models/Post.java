package goals_keeper.com.goalskeeperapp.models;

/**
 * Created by kady on 01/12/15.
 *
 * @author kady
 */
public class Post {
    // TODO: add user model !
    private String content;

    public Post(String content) {
        this.content = content;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
