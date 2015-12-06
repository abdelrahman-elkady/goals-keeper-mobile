package goals_keeper.com.goalskeeperapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.models.Goal;


/**
 * Created by kady on 01/12/15.
 *
 * @author kady
 */
public class MyGoalsAdapter extends RecyclerView.Adapter<MyGoalsAdapter.ViewHolder> {

    ArrayList<Goal> mData;
    Context mContext;

    public MyGoalsAdapter(Context mContext, ArrayList<Goal> mData) {
        super();
        this.mData = mData;
        this.mContext = mContext;
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
            public void onClick(View v) {
                if (v.isActivated()) {
                    //TODO: Remove that bad evil goal !
                    mData.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, mData.size());
                } else {
                    // By Default ALL the goals here are added to my goals so no need to have this case, just for illustration without data
                    //TODO: Add that cool goal !
                    v.setActivated(true);
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
