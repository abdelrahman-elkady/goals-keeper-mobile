package goals_keeper.com.goalskeeperapp.models;

import java.util.ArrayList;
import java.util.List;

import goals_keeper.com.goalskeeperapp.R;

/**
 * Created by abdelrahman on 01/12/15.
 */
public class User {
    private String firstName;
    private String lastName;
    private String city;
    private String country;
    private boolean gender;//true for male and false for female
    private String age;//in the data base it's date of birth .. but i will leave that for now #TODO
    private int photoId;//in the database it's a string but i will leave it like that for now as well #TODO

    private ArrayList<Like> likes;
    private ArrayList<Comment> comments;
    private ArrayList<Post> createdPosts;
    private ArrayList<Post> profilePosts;
    private ArrayList<Goal> goals;
    private ArrayList<User> followings;
    private ArrayList<User> followers;


    //#TODO i will leave this consrtuctor for now and make a new one
    User(String name, String age, int photoId) {
        this.firstName = name;
        this.age = age;
        this.photoId = photoId;
    }
    public User (String firstName , String lastName , int photoId, boolean gender)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.photoId = photoId;
        this.gender = gender;


        this.likes = new ArrayList<Like>();
        this.comments = new ArrayList<Comment>();
        this.createdPosts = new ArrayList<Post>();
        this.profilePosts = new ArrayList<Post>();
        this.goals = new ArrayList<Goal>();
        this.followings = new ArrayList<User>();
        this.followers = new ArrayList<User>();
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
