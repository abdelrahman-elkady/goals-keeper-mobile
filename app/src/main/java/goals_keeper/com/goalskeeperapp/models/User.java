package goals_keeper.com.goalskeeperapp.models;

import java.util.ArrayList;
import java.util.List;

import goals_keeper.com.goalskeeperapp.R;

/**
 * Created by abdelrahman on 01/12/15.
 */
public class User {
    public String name;
    public String age;
    public int photoId;

    User(String name, String age, int photoId) {
        this.name = name;
        this.age = age;
        this.photoId = photoId;
    }
    private List<User> users;

    // This method creates an ArrayList that has three User objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
    private void initializeData(){
        users = new ArrayList<>();
        users.add(new User("Emma Wilson", "23 years old", R.drawable.placeholder));
        users.add(new User("Lavery Maiss", "25 years old", R.drawable.placeholder));
        users.add(new User("Lillie Watts", "35 years old", R.drawable.placeholder));
    }
}
