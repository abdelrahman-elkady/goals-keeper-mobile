package goals_keeper.com.goalskeeperapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import goals_keeper.com.goalskeeperapp.fragments.PersonalGoalsFragment;
import goals_keeper.com.goalskeeperapp.fragments.TimelineFragment;

/**
 * Created by kady on 25/11/15.
 *
 * @author kady
 */
public class HomePagerAdapter extends FragmentPagerAdapter {

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new TimelineFragment();
        }
        return new PersonalGoalsFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0) {
            return "Timeline";
        }
        return "My Goals";
    }
}
