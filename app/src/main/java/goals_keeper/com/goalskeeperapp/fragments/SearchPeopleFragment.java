package goals_keeper.com.goalskeeperapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.adapters.UserListAdapter;

/**
 * Created by abdelrahman on 02/12/15.
 */
public class SearchPeopleFragment extends UserListFragment {

    @Bind(R.id.edittxt_user_list_search)
    EditText mUserSearchEditText;

    @Bind(R.id.recycler_view_user_list)
    RecyclerView mUserListRecyclerView;

    UserListAdapter mUserListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_user_list, container, false);

        ButterKnife.bind(this, view);

        mData = new ArrayList<>();
        initData();


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mUserListRecyclerView.setLayoutManager(layoutManager);

        mUserListAdapter = new UserListAdapter(getActivity(), mData);
        mUserListRecyclerView.setAdapter(mUserListAdapter);

        return view;
    }


    private void initData() {
        mData.add("Ali Hassan");
        mData.add("Abdelrahman Elkady");
        mData.add("Mohammed Mostafa");
        mData.add("Ahmed Saleh");
    }


}
