package goals_keeper.com.goalskeeperapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.adapters.SearchPagerAdapter;
import goals_keeper.com.goalskeeperapp.utils.Constants;

/**
 * Created by abdelrahman on 02/12/15.
 */
public class SearchActivity extends AppCompatActivity {
    private SearchPagerAdapter mSearchPagerAdapter;

    @Bind(R.id.search_pager)
    ViewPager mSearchViewPager;

    @Bind(R.id.search_tab_layout)
    TabLayout mSearchTabLayout;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        mToolbar.setTitle("Explore");
        setSupportActionBar(mToolbar);


        mSearchPagerAdapter = new SearchPagerAdapter(getSupportFragmentManager());
        mSearchViewPager.setAdapter(mSearchPagerAdapter);
        mSearchTabLayout.setupWithViewPager(mSearchViewPager);

        int pageNumber = getIntent().getIntExtra(Constants.VIEW_PAGER_PAGE_NUMBER, 0);
        mSearchViewPager.setCurrentItem(pageNumber);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            Intent searchIntent = new Intent(this, SearchActivity.class);
            startActivity(searchIntent);
        }

        return super.onOptionsItemSelected(item);
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
