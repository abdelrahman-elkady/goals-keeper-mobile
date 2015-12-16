package goals_keeper.com.goalskeeperapp.api;


import java.util.ArrayList;

import goals_keeper.com.goalskeeperapp.models.Goal;
import goals_keeper.com.goalskeeperapp.models.Post;
import goals_keeper.com.goalskeeperapp.models.Token;
import goals_keeper.com.goalskeeperapp.models.User;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by kady on 15/12/15.
 *
 * @author kady
 */
public interface ApiRoutes {
    @GET("/api/users")
    public Call<ArrayList<User>> listUsers();

    @POST("/api/goals")
    public Call<Void> createGoal(@Body Goal goal);

    @GET("/api/goals")
    public Call<ArrayList<Goal>> listGoals();

    @GET("/api/users/{id}")
    public Call<User> showUser(@Path("id") int userId);

    @PUT("/api/users/{id}")
    public Call<Void> editUser(@Path("id") int userId, @Body User user);

    @GET("/api/users/{id}/followings")
    public Call<ArrayList<User>> listFollowings(@Path("id") int userId);

    @GET("/api/users/{id}/followers")
    public Call<ArrayList<User>> listFollowers(@Path("id") int userId);

    @POST("/api/goals/{id}/posts")
    public Call<Void> createPost(@Path("id") int goalId, @Body Post post);

    @POST("/api/authentication")
    public Call<User> authenticate(@Body Token token);

    @GET("/api/users/{id}/goals")
    public Call<ArrayList<Goal>> getUserGoals(@Path("id") int userId);

    @POST("/api/users/{id}/goals")
    public Call<Void> addGoalToUserGoals(@Path("id") int userId, @Body Goal goal);

    @DELETE("/api/users/{id}/goals/{goal_id}")
    public Call<Void> removeGoalFromUserGoals(@Path("id") int userId, @Path("goal_id") int goalId);

    @POST("/api/users/{id}/followings/{followed_id}")
    public Call<Void> followUser(@Path("id") int userId, @Path("followed_id") int followedId);

    @DELETE("/api/users/{id}/followings/{followed_id}")
    public Call<Void> unFollowUser(@Path("id") int userId, @Path("followed_id") int followedId);

    @GET("/api/users/{id}/posts")
    public Call<ArrayList<Post>> userPosts(@Path("id") int userId);

}
