package goals_keeper.com.goalskeeperapp.models;

import java.util.ArrayList;

/**
 * Created by ali on 2/12/15.
 */
public class Goal {
    private ArrayList<User> users;
    private ArrayList<Post> posts;

    private String title;
    private String description;

    public Goal (String title)
    {
        this.title = title;
        this.description = "";
        this.users = new ArrayList<User>();
        this.posts = new ArrayList<Post>();
    }
}
