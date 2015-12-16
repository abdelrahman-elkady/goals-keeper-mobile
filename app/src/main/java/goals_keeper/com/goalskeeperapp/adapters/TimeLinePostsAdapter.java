package goals_keeper.com.goalskeeperapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.activities.CommentsActivity;
import goals_keeper.com.goalskeeperapp.models.Post;


/**
 * Created by kady on 01/12/15.
 *
 * @author kady
 */
public class TimeLinePostsAdapter extends RecyclerView.Adapter<TimeLinePostsAdapter.ViewHolder> {

    ArrayList<Post> mData;
    Context mContext;

    public TimeLinePostsAdapter(Context mContext) {
        super();
        this.mData = new ArrayList<>();
        this.mContext = mContext;
    }

    public void setmData(ArrayList<Post> mData) {
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_post_preview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.postPreviewTextView.setText(mData.get(position).getContent());

        likePost(holder);

        openComments(holder);
    }

    private void openComments(ViewHolder holder) {
        holder.commentImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CommentsActivity.class);
                // TODO: Pass the proper data
                mContext.startActivity(intent);
            }
        });
    }

    private void likePost(ViewHolder holder) {
        holder.likeImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setActivated(!v.isActivated()); // changing the icon based on the drawable
                //TODO: like/dislike the post !
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView userNameTextView, postPreviewTextView;
        ImageButton shareImageButton, commentImageButton, likeImageButton;

        public ViewHolder(View itemView) {
            super(itemView);
            userNameTextView = (TextView) itemView.findViewById(R.id.timeline_txt_user_name);
            postPreviewTextView = (TextView) itemView.findViewById(R.id.timeline_txt_post_preview);
            shareImageButton = (ImageButton) itemView.findViewById(R.id.imgbtn_share);
            likeImageButton = (ImageButton) itemView.findViewById(R.id.imgbtn_like);
            commentImageButton = (ImageButton) itemView.findViewById(R.id.imgbtn_comment);
        }

    }
}
