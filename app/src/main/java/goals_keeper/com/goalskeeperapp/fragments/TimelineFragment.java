package goals_keeper.com.goalskeeperapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.activities.CreatePostActivity;
import goals_keeper.com.goalskeeperapp.adapters.TimeLinePostsAdapter;
import goals_keeper.com.goalskeeperapp.api.Api;
import goals_keeper.com.goalskeeperapp.models.Post;
import goals_keeper.com.goalskeeperapp.utils.Constants;
import goals_keeper.com.goalskeeperapp.utils.Helpers;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

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
    SharedPreferences mSharedPreferences;

    Bundle mArguments;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_timeline, container, false);

        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);

        mSharedPreferences = getActivity().getSharedPreferences(Constants.SHARED_PREFS_KEY, Context.MODE_PRIVATE);
        mArguments = getArguments();
        if (mArguments == null) {
            mArguments = new Bundle();
        } // To check for null or allocate a new object, dilemma :D

        initToolbarTitle();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mPostsRecyclerView.setLayoutManager(layoutManager);

        mTimeLinePostsAdapter = new TimeLinePostsAdapter(getActivity());
        mPostsRecyclerView.setAdapter(mTimeLinePostsAdapter);

        initData();

        return view;
    }

    private void initToolbarTitle() {
        // Either set it to appname (my timeline) or user's timeline
        Helpers.setToolbarTitle((AppCompatActivity) getActivity(), mArguments.getString(Constants.TOOLBAR_TITLE, getString(R.string.app_name)));
    }

    private void initData() {
        mData = new ArrayList<>();
        int userId = -1;
        if (mArguments.getInt(Constants.TIMELINE_MODE, Constants.MY_TIMELINE) == Constants.USER_TIMELINE) {
            userId = mArguments.getInt(Constants.BUNDLE_USER_ID, -1);
        } else {
            userId = mSharedPreferences.getInt(Constants.USER_ID, -1);
        }
        Api.privateRoutes(getActivity()).userPosts(userId).enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Response<ArrayList<Post>> response, Retrofit retrofit) {
                mData = response.body();
                mTimeLinePostsAdapter.setmData(mData);
                mTimeLinePostsAdapter.notifyDataSetChanged();
                Log.d("POSTS LIST", response.body().toString());
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getActivity(), "Failed to retrieve posts list", Toast.LENGTH_SHORT).show();
                Log.e("POSTS LIST", t.getMessage());
            }
        });
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
