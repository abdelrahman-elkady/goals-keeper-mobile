package goals_keeper.com.goalskeeperapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import goals_keeper.com.goalskeeperapp.R;

/**
 * Created by hamamsy on 28/11/15.
 */
public class PostFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true); // To support modifying the toolbar menu on fragment change

        final View view = inflater.inflate(R.layout.fragment_post, container, false); // Inflating the fragment layout
        return view;
    }
}
