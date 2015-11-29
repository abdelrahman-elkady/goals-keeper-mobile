package goals_keeper.com.goalskeeperapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import goals_keeper.com.goalskeeperapp.R;

/**
 * Created by hamamsy on 28/11/15.
 */
public class PostAdabter extends RecyclerView.Adapter<PostAdabter.ViewHolder> {
    private String[] mDataset; //dummy data for now
    public static View mView;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout mLinearLayout;
        public ViewHolder(LinearLayout v) {
            super(v);
            mLinearLayout = v;
        }
    }

    public PostAdabter(String[] myDataset,View view) {
        mDataset = myDataset;
        mView = view;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PostAdabter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_goal_selection, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder((LinearLayout)v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView btn =(TextView) holder.mLinearLayout.findViewById(R.id.tv_checked_goal);
        btn.setText(mDataset[position]);

        final String text = btn.getText().toString();
        final TextView goalsTextView = (TextView) mView.findViewById(R.id.goal_search_edit_text);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goalsTextView.setText(text);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}