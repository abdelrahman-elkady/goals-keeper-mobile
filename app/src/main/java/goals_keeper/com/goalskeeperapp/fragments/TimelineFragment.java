package goals_keeper.com.goalskeeperapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import goals_keeper.com.goalskeeperapp.R;

/**
 * Created by kady on 25/11/15.
 *
 * @author kady
 */
public class TimelineFragment extends android.support.v4.app.Fragment {
    @Bind(R.id.fab_add_post)
    FloatingActionButton mAddPostButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_timeline, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.fab_add_post)
    public void launchCreatePost() {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        CreatePostFragment createPostFragment = new CreatePostFragment();
        fragmentTransaction.replace(R.id.main_fragment_container, createPostFragment).addToBackStack(null);
        fragmentTransaction.commit();
    }
}
