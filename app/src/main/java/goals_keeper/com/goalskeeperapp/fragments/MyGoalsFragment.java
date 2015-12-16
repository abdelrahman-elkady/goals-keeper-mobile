package goals_keeper.com.goalskeeperapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
import goals_keeper.com.goalskeeperapp.activities.SearchActivity;
import goals_keeper.com.goalskeeperapp.adapters.MyGoalsAdapter;
import goals_keeper.com.goalskeeperapp.models.Goal;
import goals_keeper.com.goalskeeperapp.utils.Constants;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_goals_preview, container, false);

        ButterKnife.bind(this, view);
        initData();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mGoalRecyclerView.setLayoutManager(layoutManager);

        mGoalsAdapter = new MyGoalsAdapter(getActivity(), mData);
        mGoalRecyclerView.setAdapter(mGoalsAdapter);

        return view;
    }

    private void initData() {
        mData = new ArrayList<>();

    }

    @OnClick(R.id.fragment_goals_preview_fab_search_goal)
    public void launchSearchGoals() {
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        intent.putExtra(Constants.VIEW_PAGER_PAGE_NUMBER, 1);
        startActivity(intent);
    }

}
