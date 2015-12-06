package goals_keeper.com.goalskeeperapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.utils.Constants;
import goals_keeper.com.goalskeeperapp.utils.Helpers;

/**
 * Created by kady on 02/12/15.
 *
 * @author kady
 */
public class UserProfileFragment extends Fragment {

    @Bind(R.id.imgbtn_follow)
    ImageButton mFollowImageButton;

    @Bind(R.id.person_timeline_btn)
    Button mPersonTimelineButton;

    @Bind(R.id.activity_profile_text_view_name)
    TextView mUsernameTextView;

    @Bind(R.id.fragment_user_profile_button_goals)
    Button mGoalsButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        setHasOptionsMenu(true); // To support modifying the toolbar menu on fragment change
        ButterKnife.bind(this, view);

        followUser();
        mUsernameTextView.setText(getArguments().getString("USER_NAME"));
        Helpers.setToolbarTitle((AppCompatActivity) getActivity(), getArguments().getString(Constants.TOOLBAR_TITLE));

        mPersonTimelineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Bundle bundle = new Bundle();
                bundle.putString(Constants.TOOLBAR_TITLE, getArguments().getString("USER_NAME") + "'s Timeline");
                bundle.putInt(Constants.TIMELINE_MODE, Constants.USER_TIMELINE);

                TimelineFragment timelineFragment = new TimelineFragment();
                timelineFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container, timelineFragment).addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        mGoalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                MyGoalsFragment myGoalsFragment = new MyGoalsFragment();
                fragmentTransaction.replace(R.id.fragment_container, myGoalsFragment).addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }

    private void followUser() {
        mFollowImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setActivated(!v.isActivated());
                //TODO: Implement following user logic
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        menu.clear();
        inflater.inflate(R.menu.menu_no_search, menu);

    }

}
