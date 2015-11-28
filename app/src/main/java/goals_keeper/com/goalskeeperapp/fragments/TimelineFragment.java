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
        mAddPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                PostFragment postFragment = new PostFragment();
                fragmentTransaction.replace(R.id.main_fragment_container, postFragment).addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return view;
    }
}
