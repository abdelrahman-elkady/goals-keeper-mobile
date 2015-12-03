package goals_keeper.com.goalskeeperapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import goals_keeper.com.goalskeeperapp.R;

/**
 * Created by kady on 03/12/15.
 *
 * @author kady
 */
public class CreateGoalFragment extends Fragment {

    @Bind(R.id.fragment_create_goal_button_cancel)
    Button mCancelButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_goal, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.fragment_create_goal_button_cancel)
    public void navigateBack() {
        getActivity().onBackPressed();
    }
}
