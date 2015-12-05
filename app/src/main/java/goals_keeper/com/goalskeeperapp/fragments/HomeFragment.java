package goals_keeper.com.goalskeeperapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.activities.AboutActivity;
import goals_keeper.com.goalskeeperapp.activities.ProfileActivity;
import goals_keeper.com.goalskeeperapp.activities.SearchActivity;
import goals_keeper.com.goalskeeperapp.adapters.HomePagerAdapter;
import goals_keeper.com.goalskeeperapp.utils.Helpers;

/**
 * Created by kady on 25/11/15.
 *
 * @author kady
 */
public class HomeFragment extends Fragment {

    private HomePagerAdapter mHomePageAdapter;

    @Bind(R.id.home_pager)
    ViewPager mHomeViewPager;

    @Bind(R.id.home_tab_layout)
    TabLayout mHomeTabLayout;

    Toolbar mToolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, view);
        Helpers.setToolbarTitle((AppCompatActivity) getActivity(), getString(R.string.app_name));

        mToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

        mHomePageAdapter = new HomePagerAdapter(getChildFragmentManager());
        mHomeViewPager.setAdapter(mHomePageAdapter);
        mHomeTabLayout.setupWithViewPager(mHomeViewPager);

        return view;
    }

}
