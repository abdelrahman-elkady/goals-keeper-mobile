package goals_keeper.com.goalskeeperapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.adapters.TimeLinePostsAdapter;
import goals_keeper.com.goalskeeperapp.models.Post;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_timeline, container, false);

        ButterKnife.bind(this, view);
        initData();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mPostsRecyclerView.setLayoutManager(layoutManager);

        mTimeLinePostsAdapter = new TimeLinePostsAdapter(getActivity(), mData);
        mPostsRecyclerView.setAdapter(mTimeLinePostsAdapter);

        return view;
    }

    private void initData() {
        mData = new ArrayList<>();
        mData.add(new Post(getResources().getString(R.string.lorem_ipsum)));
        mData.add(new Post("Hello World the world is world without any world !"));
        mData.add(new Post(getResources().getString(R.string.lorem_ipsum)));
        mData.add(new Post("Bandicoooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooots"));
        mData.add(new Post(getResources().getString(R.string.lorem_ipsum)));

    }

    @OnClick(R.id.fab_add_post)
    public void launchCreatePost() {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        CreatePostFragment createPostFragment = new CreatePostFragment();
        fragmentTransaction.replace(R.id.fragment_container, createPostFragment).addToBackStack(null);
        fragmentTransaction.commit();
    }

}
