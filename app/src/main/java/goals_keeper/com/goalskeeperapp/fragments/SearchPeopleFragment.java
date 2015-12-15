package goals_keeper.com.goalskeeperapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.adapters.UserListAdapter;
import goals_keeper.com.goalskeeperapp.api.Api;
import goals_keeper.com.goalskeeperapp.models.User;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by abdelrahman on 02/12/15.
 */
public class SearchPeopleFragment extends UserListFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_user_list, container, false);

        ButterKnife.bind(this, view);

        mData = new ArrayList<>();
        mFilteredData = new ArrayList<>();


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mUserListRecyclerView.setLayoutManager(layoutManager);

        mUserListAdapter = new UserListAdapter(getActivity(), mData);
        mUserListRecyclerView.setAdapter(mUserListAdapter);

        filterUsers();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        Api.privateRoutes(getActivity()).listUsers().enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Response<ArrayList<User>> response, Retrofit retrofit) {
                mData = response.body();
                mUserListAdapter.setData(mData);
                mUserListAdapter.notifyDataSetChanged();
                Log.d("USER LIST", response.body().toString());
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getActivity(), "Failed to retrieve user list", Toast.LENGTH_SHORT).show();
                Log.e("USER LIST", t.getMessage());
            }
        });
    }
}
