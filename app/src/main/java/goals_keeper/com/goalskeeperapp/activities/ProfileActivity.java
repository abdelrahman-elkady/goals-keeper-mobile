package goals_keeper.com.goalskeeperapp.activities;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.fragments.HomeFragment;
import goals_keeper.com.goalskeeperapp.fragments.LoginFragment;
import goals_keeper.com.goalskeeperapp.fragments.ProfileFragment;

public class ProfileActivity extends AppCompatActivity {

    @Bind(R.id.toolbar_profile)
    Toolbar mToolbar;

    private boolean hasOptionsMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Attaching the login fragment to the layout
        // TODO: Check if the user is logged in or not to replace fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ProfileFragment profileFragment = new ProfileFragment();
        fragmentTransaction.add(R.id.profile_fragment_container, profileFragment);
        fragmentTransaction.commit();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_profile) {
            Toast.makeText(this, "Profile option pressed", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setHasOptionsMenu(boolean hasOptionsMenu) {
        this.hasOptionsMenu = hasOptionsMenu;
    }
}
