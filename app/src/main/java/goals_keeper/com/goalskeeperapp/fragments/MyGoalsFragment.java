package goals_keeper.com.goalskeeperapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.activities.SearchActivity;
import goals_keeper.com.goalskeeperapp.adapters.MyGoalsAdapter;
import goals_keeper.com.goalskeeperapp.api.Api;
import goals_keeper.com.goalskeeperapp.models.Goal;
import goals_keeper.com.goalskeeperapp.utils.Constants;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by kady on 25/11/15.
 *
 * @author kady
 */
public class MyGoalsFragment extends android.support.v4.app.Fragment {
    @Bind(R.id.fragment_goals_preview_fab_search_goal)
    FloatingActionButton mSearchGoalButton;

    @Bind(R.id.fragment_goals_preview_recycler_view_goals)
    RecyclerView mGoalRecyclerView;

    MyGoalsAdapter mGoalsAdapter;
    ArrayList<Goal> mData;
    SharedPreferences mSharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_goals_preview, container, false);

        ButterKnife.bind(this, view);

        mSharedPreferences = getActivity().getSharedPreferences(Constants.SHARED_PREFS_KEY, Context.MODE_PRIVATE);
        mGoalsAdapter = new MyGoalsAdapter(getActivity());
        initData();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mGoalRecyclerView.setLayoutManager(layoutManager);

        mGoalRecyclerView.setAdapter(mGoalsAdapter);

        return view;
    }

    private void initData() {

        int userId = mSharedPreferences.getInt(Constants.USER_ID, -1);
        Api.privateRoutes(getActivity()).getUserGoals(userId).enqueue(new Callback<ArrayList<Goal>>() {
            @Override
            public void onResponse(Response<ArrayList<Goal>> response, Retrofit retrofit) {
                mData = response.body();
                mGoalsAdapter.setmData(mData);
                mGoalsAdapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "Succeeded to retrieve goals list", Toast.LENGTH_SHORT).show();
                Log.d("GOALS LIST", response.body().toString());
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getActivity(), "Failed to retrieve goals list", Toast.LENGTH_SHORT).show();
                Log.e("GOALS LIST", t.getMessage());
            }
        });

    }

    @OnClick(R.id.fragment_goals_preview_fab_search_goal)
    public void launchSearchGoals() {
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        intent.putExtra(Constants.VIEW_PAGER_PAGE_NUMBER, 1);
        startActivity(intent);
    }

}
