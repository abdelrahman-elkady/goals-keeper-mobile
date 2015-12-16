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

import java.util.ArrayList;

import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.api.Api;
import goals_keeper.com.goalskeeperapp.models.Goal;
import goals_keeper.com.goalskeeperapp.utils.Constants;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


/**
 * Created by kady on 01/12/15.
 *
 * @author kady
 */
public class MyGoalsAdapter extends RecyclerView.Adapter<MyGoalsAdapter.ViewHolder> {

    ArrayList<Goal> mData;
    Context mContext;
    SharedPreferences mSharedPreferences;

    public MyGoalsAdapter(Context mContext) {
        super();
        this.mData = new ArrayList<>();
        this.mContext = mContext;
        this.mSharedPreferences = mContext.getSharedPreferences(Constants.SHARED_PREFS_KEY, Context.MODE_PRIVATE);
    }

    public void setmData(ArrayList<Goal> mData) {

        this.mData = mData;

        if (this.mData == null) {
            this.mData = new ArrayList<>();
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_goal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.goalTitle.setText(mData.get(position).getTitle());
        holder.goalDescription.setText(mData.get(position).getDescription());

        holder.addGoalImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Goal mGoal = mData.get(position);
                int userId = mSharedPreferences.getInt(Constants.USER_ID, -1);

                if (v.isActivated()) {
                    Api.privateRoutes(mContext).removeGoalFromUserGoals(userId, mGoal.getId()).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Response<Void> response, Retrofit retrofit) {
                            if (response.code() == 200) {
                                mData.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, mData.size());
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
                    v.setActivated(true);
                    // By Default ALL the goals here are added to my goals so no need to have this case, just for illustration without data
                    //TODO: Add that cool goal !
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView goalTitle, goalDescription;
        ImageButton addGoalImageButton;

        public ViewHolder(View itemView) {
            super(itemView);
            goalTitle = (TextView) itemView.findViewById(R.id.item_goal_text_view_goal_title);
            goalDescription = (TextView) itemView.findViewById(R.id.goal_description);
            addGoalImageButton = (ImageButton) itemView.findViewById(R.id.item_goal_image_view_add_goal);
            addGoalImageButton.setActivated(true);

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
