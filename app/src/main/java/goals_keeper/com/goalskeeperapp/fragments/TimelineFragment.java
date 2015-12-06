package goals_keeper.com.goalskeeperapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.activities.CreatePostActivity;
import goals_keeper.com.goalskeeperapp.adapters.TimeLinePostsAdapter;
import goals_keeper.com.goalskeeperapp.models.Post;
import goals_keeper.com.goalskeeperapp.utils.Constants;
import goals_keeper.com.goalskeeperapp.utils.Helpers;

/**
 * Created by kady on 25/11/15.
 *
 * @author kady
 */
public class TimelineFragment extends android.support.v4.app.Fragment {

    @Bind(R.id.fab_add_post)
    FloatingActionButton mAddPostButton;

    @Bind(R.id.recycler_view_timeline)
    RecyclerView mPostsRecyclerView;

    TimeLinePostsAdapter mTimeLinePostsAdapter;
    ArrayList<Post> mData;

    Bundle mArguments;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_timeline, container, false);

        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        initData();

        mArguments = getArguments();
        if (mArguments == null) {
            mArguments = new Bundle();
        } // To check for null or allocate a new object, dilemma :D

        initToolbarTitle();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mPostsRecyclerView.setLayoutManager(layoutManager);

        mTimeLinePostsAdapter = new TimeLinePostsAdapter(getActivity(), mData);
        mPostsRecyclerView.setAdapter(mTimeLinePostsAdapter);

        return view;
    }

    private void initToolbarTitle() {
        // Either set it to appname (my timeline) or user's timeline
        Helpers.setToolbarTitle((AppCompatActivity) getActivity(), mArguments.getString(Constants.TOOLBAR_TITLE, getString(R.string.app_name)));
    }

    private void initData() {
        mData = new ArrayList<>();
        mData.add(new Post(null, getResources().getString(R.string.lorem_ipsum)));
        mData.add(new Post(null, "Hello World the world is world without any world !"));
        mData.add(new Post(null, getResources().getString(R.string.lorem_ipsum)));
        mData.add(new Post(null, "Bandicoooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooots"));
        mData.add(new Post(null, getResources().getString(R.string.lorem_ipsum)));
    }

    @OnClick(R.id.fab_add_post)
    public void launchCreatePost() {
        Intent intent = new Intent(getActivity(), CreatePostActivity.class);
        startActivity(intent);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (mArguments.getInt(Constants.TIMELINE_MODE, Constants.MY_TIMELINE) == Constants.USER_TIMELINE) {
            menu.clear();
            inflater.inflate(R.menu.menu_default, menu);
        }
    }

}
