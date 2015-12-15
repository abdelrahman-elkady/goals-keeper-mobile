package goals_keeper.com.goalskeeperapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.activities.CreateGoalActivity;
import goals_keeper.com.goalskeeperapp.adapters.GoalsListingAdapter;
import goals_keeper.com.goalskeeperapp.api.Api;
import goals_keeper.com.goalskeeperapp.models.Goal;
import goals_keeper.com.goalskeeperapp.models.User;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

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

    ArrayList<Goal> mData, mFilteredData;
    GoalsListingAdapter mSearchGoalsAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_search_goals, container, false);

        ButterKnife.bind(this, view);

        mFilteredData = new ArrayList<>();
        mData = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mGoalsRecyclerView.setLayoutManager(layoutManager);

        mSearchGoalsAdapter = new GoalsListingAdapter(getActivity(), mData);
        mGoalsRecyclerView.setAdapter(mSearchGoalsAdapter);

        filterGoals();
        return view;
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
                    if (mData.get(i).getTitle().toLowerCase().contains(query.toString().toLowerCase())) {
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

    @Override
    public void onResume() {
        super.onResume();

        Api.privateRoutes(getActivity()).listGoals().enqueue(new Callback<ArrayList<Goal>>() {
            @Override
            public void onResponse(Response<ArrayList<Goal>> response, Retrofit retrofit) {
                mData = response.body();
                mSearchGoalsAdapter.setData(mData);
                mSearchGoalsAdapter.notifyDataSetChanged();
                Log.d("GOAL LIST", response.body().toString());
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getActivity(), "Failed to retrieve goal list", Toast.LENGTH_SHORT).show();
                Log.e("GOAL LIST", t.getMessage());
            }
        });
    }

}
