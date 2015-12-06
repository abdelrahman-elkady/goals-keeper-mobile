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
 * Created by abdelrahman on 01/12/15.
 */
public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.ViewHolder> {
    ArrayList<String> mData;
    Context mContext;


    public CommentListAdapter(Context context, ArrayList<String> data) {
        super();
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentListAdapter.ViewHolder holder, int position) {
        holder.userCommentTextView.setText(mData.get(position));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView userNameTextView;
        public TextView userCommentTextView;
        public ImageView userProfileImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            userCommentTextView=(TextView)itemView.findViewById(R.id.item_comment_text_view_comment);
            userNameTextView = (TextView) itemView.findViewById(R.id.item_comment_text_view_person_name);
            userProfileImageView = (ImageView) itemView.findViewById(R.id.item_comment_image_view_profile_picture);
        }

    }
}
