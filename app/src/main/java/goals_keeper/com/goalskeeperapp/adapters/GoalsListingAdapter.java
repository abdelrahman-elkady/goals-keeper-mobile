package goals_keeper.com.goalskeeperapp.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.api.Api;
import goals_keeper.com.goalskeeperapp.models.Goal;
import goals_keeper.com.goalskeeperapp.utils.Constants;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by kady on 06/12/15.
 *
 * @author kady
 */
public class GoalsListingAdapter extends RecyclerView.Adapter<GoalsListingAdapter.ViewHolder> {


    ArrayList<Goal> mData;
    Context mContext;
    SharedPreferences mSharedPreferences;
    int mUserId;
    ArrayList<Goal> mUserAddedGoals;

    public GoalsListingAdapter(Context mContext, ArrayList<Goal> mData) {
        super();
        this.mData = mData;
        this.mContext = mContext;
        this.mSharedPreferences = mContext.getSharedPreferences(Constants.SHARED_PREFS_KEY, Context.MODE_PRIVATE);
        mUserId = mSharedPreferences.getInt(Constants.USER_ID, -1);

        fetchAddedGoals(mContext);

    }

    private void fetchAddedGoals(Context mContext) {
        //FIXME: Dangerous, two async calls with dependencies
        Api.privateRoutes(mContext).getUserGoals(mUserId).enqueue(new Callback<ArrayList<Goal>>() {
            @Override
            public void onResponse(Response<ArrayList<Goal>> response, Retrofit retrofit) {
                mUserAddedGoals = response.body();
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_goal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.goalTitle.setText(mData.get(position).getTitle());
        holder.goalDescription.setText(mData.get(position).getDescription());

        holder.addGoalImageButton.setActivated(mUserAddedGoals.contains(mData.get(position)));

        holder.addGoalImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Goal mGoal = mData.get(position);

                if (v.isActivated()) {
                    Api.privateRoutes(mContext).removeGoalFromUserGoals(mUserId, mGoal.getId()).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Response<Void> response, Retrofit retrofit) {
                            if (response.code() == 200) {
                                Toast.makeText(mContext, "Goal removed successfully", Toast.LENGTH_LONG).show();
                                v.setActivated(false); // Here we need to keep the goals as it's not my goals
                            } else {
                                Toast.makeText(mContext, "Something went wrong", Toast.LENGTH_SHORT).show();
                                Log.e("Remove GOAL", response.code() + ": " + response.message());
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            Toast.makeText(mContext, "Failed to remove goal", Toast.LENGTH_SHORT).show();
                            Log.e("CREATE GOAL", t.getMessage());
                        }
                    });

                } else {
                    Api.privateRoutes(mContext).addGoalToUserGoals(mUserId, mGoal).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Response<Void> response, Retrofit retrofit) {
                            if (response.code() == 200) {
                                Toast.makeText(mContext, "Goal added successfully", Toast.LENGTH_LONG).show();
                                v.setActivated(true);
                            } else {
                                Toast.makeText(mContext, "Something went wrong", Toast.LENGTH_SHORT).show();
                                Log.e("Add GOAL", response.code() + ": " + response.message());
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            Toast.makeText(mContext, "Failed to add goal", Toast.LENGTH_SHORT).show();
                            Log.e("CREATE GOAL", t.getMessage());
                        }
                    });
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public ArrayList<Goal> getData() {
        return mData;
    }

    public void setData(ArrayList<Goal> mData) {
        this.mData = mData;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView goalTitle, goalDescription;
        ImageButton addGoalImageButton;

        public ViewHolder(View itemView) {
            super(itemView);
            goalTitle = (TextView) itemView.findViewById(R.id.item_goal_text_view_goal_title);
            goalDescription = (TextView) itemView.findViewById(R.id.goal_description);
            addGoalImageButton = (ImageButton) itemView.findViewById(R.id.item_goal_image_view_add_goal);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            // TODO: Open goal feed activity
            Toast.makeText(mContext, "how dare you click me",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
