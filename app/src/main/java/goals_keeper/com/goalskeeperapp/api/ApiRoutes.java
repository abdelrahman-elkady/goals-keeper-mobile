package goals_keeper.com.goalskeeperapp.api;


import java.util.ArrayList;

import goals_keeper.com.goalskeeperapp.models.Goal;
import goals_keeper.com.goalskeeperapp.models.Token;
import goals_keeper.com.goalskeeperapp.models.User;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by kady on 15/12/15.
 *
 * @author kady
 */
public interface ApiRoutes {
    @GET("/api/users")
    public Call<ArrayList<User>> listUsers();

    @GET("/api/goals")
    public Call<ArrayList<Goal>> listGoals();

    @POST("/api/authentication")
    public Call<User> authenticate(@Body Token token);
}
