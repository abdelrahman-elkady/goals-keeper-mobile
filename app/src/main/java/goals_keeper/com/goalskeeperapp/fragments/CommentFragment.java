package goals_keeper.com.goalskeeperapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.adapters.CommentListAdapter;

/**
 * Created by abdelrahman on 01/12/15.
 */
public class CommentFragment extends Fragment {

    @Bind(R.id.recycler_view_comment_list)
    RecyclerView mCommentsRecyclerView;

    CommentListAdapter mCommentsAdapter;
    private ArrayList<String> mData;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment_list, container, false);

        ButterKnife.bind(this, view);

        initializeData();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mCommentsRecyclerView.setLayoutManager(layoutManager);

        mCommentsAdapter = new CommentListAdapter(getActivity(), mData);

        mCommentsRecyclerView.setAdapter(mCommentsAdapter);

        return view;
    }

    private void initializeData() {
        mData = new ArrayList<>();
        mData.add("do you like bananas ?");
        mData.add("Hello");
        mData.add("hello there");
        mData.add("Theory of computation");
        mData.add("Hamburger");
        mData.add("YaaaaY");
    }
}
