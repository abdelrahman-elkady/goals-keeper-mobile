package goals_keeper.com.goalskeeperapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.utils.Constants;

public class ProfileActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.fab_profile_edit)
    FloatingActionButton mProfileEditFAB;

    @Bind(R.id.activity_profile_button_followers)
    Button mFollowersButton;

    @Bind(R.id.activity_profile_button_following)
    Button mFollowingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);

        mToolbar.setTitle("Your Profile");
        setSupportActionBar(mToolbar);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    @OnClick(R.id.fab_profile_edit)
    public void editProfile() {
        Intent editProfileIntent = new Intent(this, ProfileEditActivity.class);
        startActivity(editProfileIntent);
    }

    @OnClick(R.id.activity_profile_button_following)
    public void showFollowing() {
        Intent followingIntent = new Intent(this, UserConnectionsActivity.class);

        ArrayList<String> data = new ArrayList<String>();
        data.add("Ali Hassan");
        data.add("Abdelrahman Elkady");
        data.add("Mohammed Mostafa");
        data.add("Ahmed Saleh");

        followingIntent.putStringArrayListExtra(Constants.BUNDLE_USERS_KEY, data);
        followingIntent.putExtra(Constants.USER_CONNECTION_TYPE, Constants.FOLLOWINGS);
        startActivity(followingIntent);
    }

    @OnClick(R.id.activity_profile_button_followers)
    public void showFollowers() {
        Intent followersIntent = new Intent(this, UserConnectionsActivity.class);

        ArrayList<String> data = new ArrayList<String>();
        data.add("Ali Hassan");
        data.add("Ahmed Saleh");

        followersIntent.putStringArrayListExtra(Constants.BUNDLE_USERS_KEY, data);
        followersIntent.putExtra(Constants.USER_CONNECTION_TYPE, Constants.FOLLOWERS);
        startActivity(followersIntent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_search:
                Intent searchIntent = new Intent(this, SearchActivity.class);
                startActivity(searchIntent);
                return true;
            case R.id.action_about:
                Intent aboutIntent = new Intent(this, AboutActivity.class);
                startActivity(aboutIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }
}
