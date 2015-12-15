package goals_keeper.com.goalskeeperapp.api;


import java.util.ArrayList;

import goals_keeper.com.goalskeeperapp.models.Goal;
import goals_keeper.com.goalskeeperapp.models.Token;
import goals_keeper.com.goalskeeperapp.models.User;
import retrofit.Call;
import retrofit.http.Body;
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
    public Call<Void> editUser(@Body User user);

    @POST("/api/authentication")
    public Call<User> authenticate(@Body Token token);
}
