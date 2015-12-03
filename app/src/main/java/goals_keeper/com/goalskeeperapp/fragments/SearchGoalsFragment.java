package goals_keeper.com.goalskeeperapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.activities.CreateGoalActivity;
import goals_keeper.com.goalskeeperapp.adapters.SearchGoalsAdapter;
import goals_keeper.com.goalskeeperapp.adapters.SearchPagerAdapter;

/**
 * Created by abdelrahman on 02/12/15.
 */
public class SearchGoalsFragment extends android.support.v4.app.Fragment {
    @Bind(R.id.recycler_view_search_goals)
    RecyclerView mGoalsRecyclerView;

    @Bind(R.id.edittxt_search_goals)
    EditText mGoalsSearchEditText;

    @Bind(R.id.fragment_search_goals_fab_create_goal)
    FloatingActionButton mCreateGoalFloatingActionButton;

    ArrayList<String> mData, mFilteredData;
    SearchGoalsAdapter mSearchGoalsAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_search_goals, container, false);

        ButterKnife.bind(this, view);

        mData = new ArrayList<>();
        mFilteredData = new ArrayList<>();
        initData();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mGoalsRecyclerView.setLayoutManager(layoutManager);

        mSearchGoalsAdapter = new SearchGoalsAdapter(getActivity(), mData);
        mGoalsRecyclerView.setAdapter(mSearchGoalsAdapter);

        filterGoals();
        return view;
    }


    private void initData() {
        mData.add("Quit smoking");
        mData.add("Getting A+ in theory of computation");
        mData.add("Get driving license");
        mData.add("Eat a banana");
        mData.add("Win a marathon");
        mData.add("Win a Free Sandwich");

    }

    @OnClick(R.id.fragment_search_goals_fab_create_goal)
    public void createGoal() {
        Intent intent = new Intent(getActivity(), CreateGoalActivity.class);
        startActivity(intent);
    }

    protected void filterGoals() {
        mGoalsSearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence query, int start, int before, int count) {
                mFilteredData.clear();
                for (int i = 0; i < mData.size(); i++) {
                    if (mData.get(i).toLowerCase().contains(query.toString().toLowerCase())) {
                        mFilteredData.add(mData.get(i));
                    }
                }
                mSearchGoalsAdapter.setData(mFilteredData);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


}
