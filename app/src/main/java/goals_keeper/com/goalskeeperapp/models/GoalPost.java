package goals_keeper.com.goalskeeperapp.models;

/**
 * Created by ali on 2/12/15.
 */
public class GoalPost extends Post{
    private Goal goal;

    public GoalPost(User creator,String content, Goal goal)
    {
        super(creator,content);
        this.goal  = goal;
    }
}
