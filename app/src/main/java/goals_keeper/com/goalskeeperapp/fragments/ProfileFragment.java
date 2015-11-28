package goals_keeper.com.goalskeeperapp.fragments;

import android.content.Context;
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
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import goals_keeper.com.goalskeeperapp.R;

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
    private Context applicationContext;

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
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                ProfileEditFragment profileEditFragment = new ProfileEditFragment();
                fragmentTransaction.replace(R.id.profile_fragment_container, profileEditFragment).addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        mFollowingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// TODO Add the linking to the Ali's Fragments/Activities
                Toast.makeText(getActivity(), "Waiting for Ali's task", Toast.LENGTH_LONG).show();

            }
        });
        mFollowersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// TODO Add the linking to the Ali's Fragments/Activities
                    Toast.makeText(getActivity(), "Waiting for Ali's task", Toast.LENGTH_LONG).show();
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

        return super.onOptionsItemSelected(item);
    }

}
