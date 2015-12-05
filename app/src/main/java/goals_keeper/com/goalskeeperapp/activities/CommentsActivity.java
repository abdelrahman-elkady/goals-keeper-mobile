package goals_keeper.com.goalskeeperapp.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.fragments.CommentListFragment;
import goals_keeper.com.goalskeeperapp.utils.Constants;

/**
 * Created by kady on 06/12/15.
 *
 * @author kady
 */
public class CommentsActivity extends BaseActivity {


    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    ArrayList<String> mData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);

        mToolbar.setTitle("Comments");
        setSupportActionBar(mToolbar);
        initData();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(R.id.fragment_container, CommentListFragment.newInstance(mData));
        transaction.commit();

    }


    private void initData() {
        mData = new ArrayList<>();
        mData.add("do you like bananas ?");
        mData.add("Hello");
        mData.add("hello there");
        mData.add("Theory of computation");
        mData.add("Hamburger");
        mData.add("YaaaaY");
    }
}
