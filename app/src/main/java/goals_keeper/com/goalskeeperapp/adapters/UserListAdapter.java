package goals_keeper.com.goalskeeperapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.activities.UserProfileActivity;
import goals_keeper.com.goalskeeperapp.models.User;
import goals_keeper.com.goalskeeperapp.utils.Constants;

/**
 * Created by kady on 30/11/15.
 *
 * @author kady
 */
public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    ArrayList<User> mData;
    Context mContext;

    public UserListAdapter(Context context, ArrayList<User> data) {
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
        User item = mData.get(position);

        holder.userNameTextView.setText(item.getName());
        Picasso.with(mContext).load(item.getProfilePicture()).into(holder.userProfileImageView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(ArrayList<User> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView userNameTextView;
        public ImageView userProfileImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            userNameTextView = (TextView) itemView.findViewById(R.id.txtview_user_name);
            userProfileImageView = (ImageView) itemView.findViewById(R.id.item_comment_image_view_profile_picture);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // TODO: As this adapter is generic, make the user implement the onClick better by calling a method from interface
            Bundle bundle = new Bundle();

            //TODO: Convert the whole object to JSON ?
            User item = mData.get(getAdapterPosition());
            bundle.putString(Constants.BUNDLE_USER_NAME, item.getName());
            bundle.putString(Constants.BUNDLE_USER_ID, item.getId());
            bundle.putString(Constants.TOOLBAR_TITLE, String.format("%s's profile", item));

            Intent userProfileIntent = new Intent(mContext, UserProfileActivity.class);
            userProfileIntent.putExtras(bundle);
            mContext.startActivity(userProfileIntent);

        }
    }
}
