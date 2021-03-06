package goals_keeper.com.goalskeeperapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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
import goals_keeper.com.goalskeeperapp.adapters.GoalsListingAdapter;
import goals_keeper.com.goalskeeperapp.models.Goal;
import goals_keeper.com.goalskeeperapp.utils.Constants;

/**
 * Created by kady on 06/12/15.
 *
 * @author kady
 */
public class GoalsListingFragment extends Fragment {

    @Bind(R.id.fragment_goals_preview_fab_search_goal)
    FloatingActionButton mSearchGoalButton;

    @Bind(R.id.fragment_goals_preview_recycler_view_goals)
    RecyclerView mGoalRecyclerView;

    GoalsListingAdapter mGoalsAdapter;
    ArrayList<Goal> mData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goals_preview, container, false);
        ButterKnife.bind(this, view);

        initData();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mGoalRecyclerView.setLayoutManager(layoutManager);

        mGoalsAdapter = new GoalsListingAdapter(getActivity(), mData);
        mGoalRecyclerView.setAdapter(mGoalsAdapter);

        return view;
    }


    private void initData() {
        mData = new ArrayList<>();
//        mData.add(new Goal("A+", "gotta catch 'em all "));
//        mData.add(new Goal("Some cool goal", "Because Repetition is bad !"));
//        mData.add(new Goal("quit smoking", "i don't think cancer is cool"));
//        mData.add(new Goal("Space Travel", "Want to try something interesting !"));
//        mData.add(new Goal("becoming a bird", "so i can have colorful feathers"));
//        mData.add(new Goal("Finish projects", "It seems impossible to finish projects :|"));
//        mData.add(new Goal("leaving egypt", "we are not meant to save egypt .. we are meant to leave it "));

    }

    @OnClick(R.id.fragment_goals_preview_fab_search_goal)
    public void launchSearchGoals() {
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        intent.putExtra(Constants.VIEW_PAGER_PAGE_NUMBER, 1);
        startActivity(intent);
    }
}
