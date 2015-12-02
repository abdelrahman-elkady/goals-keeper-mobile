package goals_keeper.com.goalskeeperapp.models;

/**
 * Created by ali on 2/12/15.
 */
public class ProfilePost extends Post{
    private User profile;

    public ProfilePost(User creator,String content, User profile)
    {
        super(creator,content);
        this.profile  = profile;
    }
}
