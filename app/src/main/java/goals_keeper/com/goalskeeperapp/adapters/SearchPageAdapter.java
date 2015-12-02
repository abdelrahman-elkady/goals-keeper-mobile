package goals_keeper.com.goalskeeperapp.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import goals_keeper.com.goalskeeperapp.fragments.SearchGoalsFragment;
import goals_keeper.com.goalskeeperapp.fragments.SearchPeopleFragment;
import goals_keeper.com.goalskeeperapp.models.Post;

/**
 * Created by abdelrahman on 02/12/15.
 */
public class SearchPageAdapter extends FragmentPagerAdapter {

    public SearchPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public Fragment getItem(int position) {
        if (position == 0) {
            return new SearchPeopleFragment();
        }
        return new SearchGoalsFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0) {
            return "Find People";
        }
        return "Explore Goals";
    }
}


