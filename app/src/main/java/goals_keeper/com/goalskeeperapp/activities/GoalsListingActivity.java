package goals_keeper.com.goalskeeperapp.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import butterknife.Bind;
import butterknife.ButterKnife;
import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.fragments.GoalsListingFragment;

/**
 * Created by kady on 06/12/15.
 *
 * @author kady
 */
public class GoalsListingActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals_listing);

        ButterKnife.bind(this);

        mToolbar.setTitle("Goals"); //TODO: could be prefixed with the user's name

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        GoalsListingFragment fragment = new GoalsListingFragment();

        transaction.add(R.id.fragment_container, fragment).commit();

    }

}
