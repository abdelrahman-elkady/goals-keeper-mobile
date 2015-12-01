package goals_keeper.com.goalskeeperapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.adapters.UserListAdapter;
import goals_keeper.com.goalskeeperapp.utils.Constants;
import goals_keeper.com.goalskeeperapp.utils.Helpers;

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

    ArrayList<String> mFilteredData, mData;

    private Bundle mBundle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_user_list, container, false);

        ButterKnife.bind(this, root);

        mBundle = getArguments();

        mData = mBundle.getStringArrayList(Constants.BUNDLE_USERS_KEY);
        int type = mBundle.getInt(Constants.USER_CONNECTION_TYPE, 0);
        changeTitle(type);

        mFilteredData = new ArrayList<>();


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mUserListRecyclerView.setLayoutManager(layoutManager);


        mUserListAdapter = new UserListAdapter(getActivity(), mData);
        mUserListRecyclerView.setAdapter(mUserListAdapter);

        filterUsers();

        return root;
    }

    private void changeTitle(int type) {
        switch (type) {
            case (Constants.FOLLOWERS):
                Helpers.setToolbarTitle((AppCompatActivity) getActivity(), "Your followers");
                break;
            case (Constants.FOLLOWINGS):
                Helpers.setToolbarTitle((AppCompatActivity) getActivity(), "People you follow");
                break;
            // TODO: You can add default after modifying the default value while getting the type
        }
    }

    /**
     * Filter users in list based on the query in EditText
     */
    private void filterUsers() {
        mUserSearchEditText.addTextChangedListener(new TextWatcher() {
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
                mUserListAdapter.setData(mFilteredData);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}
