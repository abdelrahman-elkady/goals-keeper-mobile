package goals_keeper.com.goalskeeperapp.models;

import java.util.ArrayList;

/**
 * Created by kady on 01/12/15.
 *
 * @author kady
 */
public class Post {

    private Integer id;
    private String text;
    private Boolean _private;
    private Integer userId;
    private Integer goalId;



    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text The text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return The _private
     */
    public Boolean getPrivate() {
        return _private;
    }

    /**
     * @param _private The private
     */
    public void setPrivate(Boolean _private) {
        this._private = _private;
    }

    /**
     * @return The userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId The user_id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return The goalId
     */
    public Integer getGoalId() {
        return goalId;
    }

    /**
     * @param goalId The goal_id
     */
    public void setGoalId(Integer goalId) {
        this.goalId = goalId;
    }


}
