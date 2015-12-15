package goals_keeper.com.goalskeeperapp.models;


/**
 * Created by ali on 2/12/15.
 */
public class Goal {

    private String title;
    private String description;

    public Goal() {

    }

    public Goal(String title, String description) {
        this.title = title;
        this.description = description;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }


}