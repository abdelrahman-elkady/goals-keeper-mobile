package goals_keeper.com.goalskeeperapp.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.fragments.LoginFragment;
import goals_keeper.com.goalskeeperapp.fragments.UserListFragment;
import goals_keeper.com.goalskeeperapp.utils.Constants;

/**
 * Created by kady on 30/11/15.
 *
 * @author kady
 */
public class FollowsActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        ArrayList<String> data = getIntent().getStringArrayListExtra(Constants.BUNDLE_USERS_KEY);

        Bundle bundle = new Bundle();
        bundle.putStringArrayList(Constants.BUNDLE_USERS_KEY, data);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        UserListFragment userListFragment = new UserListFragment();
        userListFragment.setArguments(bundle);
        fragmentTransaction.add(R.id.fragment_container, userListFragment);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
