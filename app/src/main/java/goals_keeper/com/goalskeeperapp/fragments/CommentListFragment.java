package goals_keeper.com.goalskeeperapp.fragments;

import android.content.Context;
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
import goals_keeper.com.goalskeeperapp.utils.Constants;

/**
 * Created by abdelrahman on 01/12/15.
 */
public class CommentListFragment extends Fragment {

    @Bind(R.id.fragment_comment_list_recycler_view_comments)
    RecyclerView mCommentsRecyclerView;

    CommentListAdapter mCommentsAdapter;
    private ArrayList<String> mData;


    public static CommentListFragment newInstance(ArrayList<String> data) {

        Bundle args = new Bundle();

        CommentListFragment fragment = new CommentListFragment();
        args.putStringArrayList(Constants.DATA, data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mData = getArguments().getStringArrayList(Constants.DATA);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment_list, container, false);

        ButterKnife.bind(this, view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mCommentsRecyclerView.setLayoutManager(layoutManager);

        mCommentsAdapter = new CommentListAdapter(getActivity(), mData);

        mCommentsRecyclerView.setAdapter(mCommentsAdapter);

        return view;
    }

}
