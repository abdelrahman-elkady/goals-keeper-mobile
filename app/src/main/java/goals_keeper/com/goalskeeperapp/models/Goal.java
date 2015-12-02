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

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Goal (String title)
    {
        this.title = title;
        this.description = "";
        this.users = new ArrayList<User>();
        this.posts = new ArrayList<Post>();
    }
    public Goal (String title,String description)
    {
        this(title);
        this.description = description;

    }
}
