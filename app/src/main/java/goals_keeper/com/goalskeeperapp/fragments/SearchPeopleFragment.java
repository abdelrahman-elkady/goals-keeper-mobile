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
import goals_keeper.com.goalskeeperapp.adapters.SearchPageAdapter;
import goals_keeper.com.goalskeeperapp.adapters.UserListAdapter;
import goals_keeper.com.goalskeeperapp.models.User;

/**
 * Created by abdelrahman on 02/12/15.
 */
public class SearchPeopleFragment extends android.support.v4.app.Fragment {

    @Bind(R.id.recycler_view_search_people)
    RecyclerView mPeopleRecyclerView;

    UserListAdapter mUsersAdapter;
    ArrayList<String> mData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_search_goals, container, false);

        ButterKnife.bind(this, view);
        initData();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mPeopleRecyclerView.setLayoutManager(layoutManager);

        mUsersAdapter = new UserListAdapter(getActivity(),mData);
        mPeopleRecyclerView.setAdapter(mUsersAdapter);

        return view;
    }


    private void initData() {
        mData.add("Quit smoking");
        mData.add("Getting A+ in theory of computation");
        mData.add("Get driving license");
        mData.add("Eat a banana");
        mData.add("Win a marathon");
        mData.add("Win a Free Sandwish");

    }


}
