package goals_keeper.com.goalskeeperapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.adapters.UserListAdapter;
import goals_keeper.com.goalskeeperapp.utils.Constants;

/**
 * Created by kady on 30/11/15.
 *
 * @author kady
 */
public class UserListFragment extends Fragment {

    @Bind(R.id.edittxt_user_list_search)
    EditText mUserSearchEditText;

    @Bind(R.id.recycler_view_user_list)
    RecyclerView mUserListRecyclerView;

    UserListAdapter mUserListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_user_list, container, false);

        ButterKnife.bind(this, root);

        ArrayList<String> data = getArguments().getStringArrayList(Constants.BUNDLE_USERS_KEY);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mUserListRecyclerView.setLayoutManager(layoutManager);


        mUserListAdapter = new UserListAdapter(getActivity(), data);

        mUserListRecyclerView.setAdapter(mUserListAdapter);

        return root;
    }
}
