package goals_keeper.com.goalskeeperapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.activities.SearchActivity;
import goals_keeper.com.goalskeeperapp.activities.UserConnectionsActivity;
import goals_keeper.com.goalskeeperapp.utils.Constants;

/**
 * Created by kady on 27/11/15.
 *
 * @author kady
 */
public class ProfileFragment extends Fragment {

    @Bind(R.id.fab_profile_edit)
    FloatingActionButton mProfileEditFAB;
    @Bind(R.id.followers_button)
    Button mFollowersButton;
    @Bind(R.id.following_button)
    Button mFollowingButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true); // To support modifying the toolbar menu on fragment change

        final View view = inflater.inflate(R.layout.fragment_profile, container, false); // Inflating the fragment layout

        ButterKnife.bind(this, view);

        mProfileEditFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                ProfileEditFragment profileEditFragment = new ProfileEditFragment();
                fragmentTransaction.replace(R.id.fragment_container, profileEditFragment).addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        mFollowingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent followingIntent = new Intent(getActivity(), UserConnectionsActivity.class);

                ArrayList<String> data = new ArrayList<String>();
                data.add("Ali Hassan");
                data.add("Abdelrahman Elkady");
                data.add("Mohammed Mostafa");
                data.add("Ahmed Saleh");

                followingIntent.putStringArrayListExtra(Constants.BUNDLE_USERS_KEY, data);
                followingIntent.putExtra(Constants.USER_CONNECTION_TYPE, Constants.FOLLOWINGS);
                startActivity(followingIntent);

            }
        });

        mFollowersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent followersIntent = new Intent(getActivity(), UserConnectionsActivity.class);

                ArrayList<String> data = new ArrayList<String>();
                data.add("Ali Hassan");
                data.add("Ahmed Saleh");

                followersIntent.putStringArrayListExtra(Constants.BUNDLE_USERS_KEY, data);
                followersIntent.putExtra(Constants.USER_CONNECTION_TYPE, Constants.FOLLOWERS);
                startActivity(followersIntent);
            }
        });

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().onBackPressed();
            return true;
        }

        if (item.getItemId() == R.id.action_search) {
            Intent searchIntent = new Intent(this.getActivity(), SearchActivity.class);
            startActivity(searchIntent);
        }

        return super.onOptionsItemSelected(item);
    }

}
