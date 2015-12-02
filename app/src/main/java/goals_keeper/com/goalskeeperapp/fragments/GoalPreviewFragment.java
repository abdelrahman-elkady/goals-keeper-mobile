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
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.adapters.GoalsPreviewAdapter;
import goals_keeper.com.goalskeeperapp.adapters.TimeLinePostsAdapter;
import goals_keeper.com.goalskeeperapp.models.Goal;
import goals_keeper.com.goalskeeperapp.models.Post;

/**
 * Created by kady on 25/11/15.
 *
 * @author kady
 */
public class GoalPreviewFragment extends android.support.v4.app.Fragment {
    @Bind(R.id.fab_search_goal)
    FloatingActionButton mSearchGoalButton;

    @Bind(R.id.recycler_view_goals)
    RecyclerView mGoalRecyclerView;

    GoalsPreviewAdapter mGoalsPreviewAdapter;
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

        mGoalsPreviewAdapter = new GoalsPreviewAdapter(getActivity(), mData);
        mGoalRecyclerView.setAdapter(mGoalsPreviewAdapter);

        return view;
    }
    private void initData() {
        mData = new ArrayList<>();
        mData.add(new Goal("A+","gotta catch 'em all "));
        mData.add(new Goal("quit smoking","i don't think cancer is cool"));
        mData.add(new Goal("becoming a bird","so i can have colorful deathers"));
        mData.add(new Goal("leaving egypt","we are not meant to save egypt .. we are meant to leave it "));

    }

    @OnClick(R.id.fab_search_goal)
    public void launchCreatePost() {
        Toast.makeText(getActivity().getBaseContext(), "You clciked ", Toast.LENGTH_SHORT).show();
        /*FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        CreatePostFragment createPostFragment = new CreatePostFragment();
        fragmentTransaction.replace(R.id.fragment_container, createPostFragment).addToBackStack(null);
        fragmentTransaction.commit();*/
    }

}
