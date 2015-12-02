package goals_keeper.com.goalskeeperapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.adapters.SearchGoalsAdapter;
import goals_keeper.com.goalskeeperapp.adapters.SearchPagerAdapter;

/**
 * Created by abdelrahman on 02/12/15.
 */
public class SearchGoalsFragment extends android.support.v4.app.Fragment {
    @Bind(R.id.recycler_view_search_goals)
    RecyclerView mGoalsRecyclerView;

    ArrayList<String> mData;
    SearchGoalsAdapter mSearchGoalsAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_search_goals, container, false);

        ButterKnife.bind(this, view);

        mData = new ArrayList<>();
        initData();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mGoalsRecyclerView.setLayoutManager(layoutManager);

        mSearchGoalsAdapter = new SearchGoalsAdapter(getActivity(), mData);
        mGoalsRecyclerView.setAdapter(mSearchGoalsAdapter);

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


}
