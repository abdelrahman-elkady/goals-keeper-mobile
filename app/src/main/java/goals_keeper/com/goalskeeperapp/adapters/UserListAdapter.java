package goals_keeper.com.goalskeeperapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import goals_keeper.com.goalskeeperapp.R;

/**
 * Created by kady on 30/11/15.
 *
 * @author kady
 */
public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    ArrayList<String> mData;
    Context mContext;

    public UserListAdapter(Context context, ArrayList<String> data) {
        super();
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String item = mData.get(position);

        //TODO: set the profile pic
        holder.userNameTextView.setText(item);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView userNameTextView;
        public ImageView userProfileImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            userNameTextView = (TextView) itemView.findViewById(R.id.txtview_user_name);
            userProfileImageView = (ImageView) itemView.findViewById(R.id.imgview_profile_picture);
        }

    }
}
