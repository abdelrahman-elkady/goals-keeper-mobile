package goals_keeper.com.goalskeeperapp.adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.fragments.CommentFragment;
import goals_keeper.com.goalskeeperapp.models.Goal;
import goals_keeper.com.goalskeeperapp.models.Post;


/**
 * Created by kady on 01/12/15.
 *
 * @author kady
 */
public class GoalsPreviewAdapter extends RecyclerView.Adapter<GoalsPreviewAdapter.ViewHolder> {

    ArrayList<Goal> mData;
    Context mContext;

    public GoalsPreviewAdapter(Context mContext, ArrayList<Goal> mData) {
        super();
        this.mData = mData;
        this.mContext = mContext;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_goal_preview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.goalTitle.setText(mData.get(position).getTitle());
        holder.goalDescription.setText(mData.get(position).getDescription());

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView goalTitle, goalDescription;
        public ViewHolder(View itemView) {
            super(itemView);
            goalTitle = (TextView) itemView.findViewById(R.id.goal_title);
            goalDescription = (TextView) itemView.findViewById(R.id.goal_description);
        }


        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "how dare you click me",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
