package goals_keeper.com.goalskeeperapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import goals_keeper.com.goalskeeperapp.R;

/**
 * Created by kady on 02/12/15.
 *
 * @author kady
 */
public class SearchGoalsAdapter extends RecyclerView.Adapter<SearchGoalsAdapter.ViewHolder> {

    ArrayList<String> mData;
    Context mContext;


    public SearchGoalsAdapter(Context mContext, ArrayList<String> mData) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_goal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String item = mData.get(position);

        //TODO: set the profile pic
        holder.goalTitleTextView.setText(item);
    }

    public void setData(ArrayList<String> data) {
        this.mData = data;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView goalTitleTextView;
        public ImageView addToMyGoalsImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            goalTitleTextView = (TextView) itemView.findViewById(R.id.item_goal_txt_goal_title);
            addToMyGoalsImageView = (ImageView) itemView.findViewById(R.id.item_goal_imgview_add_goal);
        }
    }
}
