package goals_keeper.com.goalskeeperapp.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.fragments.UserProfileFragment;
import goals_keeper.com.goalskeeperapp.utils.Constants;

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

    public void setData(ArrayList<String> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView userNameTextView;
        public ImageView userProfileImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            userNameTextView = (TextView) itemView.findViewById(R.id.txtview_user_name);
            userProfileImageView = (ImageView) itemView.findViewById(R.id.imgview_profile_picture);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // TODO: As this adapter is generic, make the user implement the onClick better by calling a method from interface
            FragmentManager manager = ((AppCompatActivity) mContext).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = manager.beginTransaction();

            Bundle bundle = new Bundle();

            //TODO: Convert the whole object to JSON ?
            String item = mData.get(getAdapterPosition());
            bundle.putString("USER_NAME", item);
            bundle.putString(Constants.TOOLBAR_TITLE, String.format("%s's profile", item));

            UserProfileFragment fragment = new UserProfileFragment();
            fragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack(null);
            fragmentTransaction.commit();

        }
    }
}
