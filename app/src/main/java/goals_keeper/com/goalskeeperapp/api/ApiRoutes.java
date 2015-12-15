package goals_keeper.com.goalskeeperapp.api;


import java.util.ArrayList;

import goals_keeper.com.goalskeeperapp.models.User;
import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by kady on 15/12/15.
 *
 * @author kady
 */
public interface ApiRoutes {
    @GET("/api/users")
    public Call<ArrayList<User>> listUsers();
}
