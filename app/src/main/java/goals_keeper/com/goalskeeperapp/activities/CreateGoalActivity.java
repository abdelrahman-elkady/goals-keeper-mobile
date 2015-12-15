package goals_keeper.com.goalskeeperapp.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.api.Api;
import goals_keeper.com.goalskeeperapp.models.Goal;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by kady on 03/12/15.
 *
 * @author kady
 */
public class CreateGoalActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.activity_create_goal_edit_text_title)
    EditText mGoalTitleEditText;

    @Bind(R.id.activity_create_goal_edit_text_description)
    EditText mGoalDescriptionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_goal);

        ButterKnife.bind(this);

        mToolbar.setTitle("New Goal");
        setSupportActionBar(mToolbar);

    }

    @OnClick(R.id.activity_create_goal_button_create)
    public void createGoal() {
        Goal goal = new Goal(mGoalTitleEditText.getText().toString(), mGoalDescriptionEditText.getText().toString());
        Api.privateRoutes(this).createGoal(goal).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Response<Void> response, Retrofit retrofit) {
                if (response.code() == 200) {
                    Toast.makeText(CreateGoalActivity.this, "Goal created successfully", Toast.LENGTH_LONG).show();
                    onBackPressed();
                } else {
                    Toast.makeText(CreateGoalActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    Log.e("CREATE GOAL", response.code() + ": " + response.message());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(CreateGoalActivity.this, "Failed to create a new goal", Toast.LENGTH_SHORT).show();
                Log.e("CREATE GOAL", t.getMessage());
            }
        });
    }

    @OnClick(R.id.activity_create_goal_button_cancel)
    public void navigateBack() {
        onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_default, menu);
        return true;
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
