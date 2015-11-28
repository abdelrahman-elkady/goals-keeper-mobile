package goals_keeper.com.goalskeeperapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.adapters.PostAdabter;

/**
 * Created by hamamsy on 28/11/15.
 */
public class PostFragment extends android.support.v4.app.Fragment {
    @Bind(R.id.post_recycler_view)
    RecyclerView mRecyclerView;

    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post, container, false); // Inflating the fragment layout

        ButterKnife.bind(this, view);

        // use a linear layout manager
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        String []dummyData = new String[10];
        for (int i=0;i<dummyData.length ;i++)
            dummyData[i] = new String("Goal "+(i+1));
        // specify an adapter (see also next example)
        mAdapter = new PostAdabter(dummyData);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }
}
