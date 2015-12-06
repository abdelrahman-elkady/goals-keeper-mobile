package goals_keeper.com.goalskeeperapp.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import goals_keeper.com.goalskeeperapp.fragments.MyGoalsFragment;
import goals_keeper.com.goalskeeperapp.fragments.TimelineFragment;
import goals_keeper.com.goalskeeperapp.utils.Constants;

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
            Bundle bundle = new Bundle();
            bundle.putInt(Constants.TIMELINE_MODE, Constants.MY_TIMELINE);
            TimelineFragment timelineFragment = new TimelineFragment();
            timelineFragment.setArguments(bundle);
            return timelineFragment;
        }
        return new MyGoalsFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Timeline";
        }
        return "My Goals";
    }
}
