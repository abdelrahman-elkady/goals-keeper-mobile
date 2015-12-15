package goals_keeper.com.goalskeeperapp.activities;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import goals_keeper.com.goalskeeperapp.BuildConfig;
import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.activities.base.BaseActivity;

/**
 * Created by kady on 05/12/15.
 *
 * @author kady
 */
public class AboutActivity extends BaseActivity {

    @Bind(R.id.activity_about_text_view_version)
    TextView mVersionStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        String version = BuildConfig.VERSION_NAME;
        mVersionStatus.setText("v " + version);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        return false; // don't create a menu here !
    }
}
