package goals_keeper.com.goalskeeperapp.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.activities.base.BaseActivity;
import goals_keeper.com.goalskeeperapp.adapters.CreatePostAutoCompleteAdapter;
import goals_keeper.com.goalskeeperapp.api.Api;
import goals_keeper.com.goalskeeperapp.models.Goal;
import goals_keeper.com.goalskeeperapp.models.Post;
import goals_keeper.com.goalskeeperapp.utils.Constants;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by kady on 05/12/15.
 *
 * @author kady
 */
public class CreatePostActivity extends BaseActivity {
    @Bind(R.id.activity_create_post_button_cancel)
    Button mCancelButton;

    @Bind(R.id.activity_create_post_edit_text_goal_select)
    AutoCompleteTextView mGoalSelectAutoCompleteTextView;

    @Bind(R.id.activity_create_post_button_post)
    Button mPostButton;

    @Bind(R.id.activity_create_post_edit_text_post_body)
    EditText mBodyEditText;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    CreatePostAutoCompleteAdapter mGoalsAdapter;
    ArrayList<Goal> mData;

    SharedPreferences mSharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        ButterKnife.bind(this);

        mSharedPreferences = getSharedPreferences(Constants.SHARED_PREFS_KEY, MODE_PRIVATE);

        mToolbar.setTitle("New Post");
        setSupportActionBar(mToolbar);

        mData = new ArrayList<>();

        mGoalsAdapter = new CreatePostAutoCompleteAdapter(this, mData);
        mGoalSelectAutoCompleteTextView.setAdapter(mGoalsAdapter);

        validateInput();

    }

    @Override
    protected void onResume() {
        super.onResume();


        Api.privateRoutes(this).getUserGoals(mSharedPreferences.getInt(Constants.USER_ID, -1)).enqueue(new Callback<ArrayList<Goal>>() {
            @Override
            public void onResponse(Response<ArrayList<Goal>> response, Retrofit retrofit) {
                mData = response.body();
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }

    @OnClick(R.id.activity_create_post_button_cancel)
    public void navigateBack() {
        onBackPressed();
    }

    @OnClick(R.id.activity_create_post_button_post)
    void createPost() {
        int index = arraylistToStringArray(mData).indexOf(mGoalSelectAutoCompleteTextView.getText().toString());
        int goalId = mData.get(index).getId();
        String postText = mBodyEditText.getText().toString();

        Post post = new Post();
        post.setGoalId(goalId);
        post.setText(postText);

        Api.privateRoutes(this).createPost(goalId, post).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Response<Void> response, Retrofit retrofit) {
                Toast.makeText(CreatePostActivity.this, "Posted a new story successfully", Toast.LENGTH_LONG).show();
                onBackPressed();
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }


    /**
     * Checks for the goal to be within the goals of the user
     */
    private void validateInput() {
        mGoalSelectAutoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (arraylistToStringArray(mData).contains(mGoalSelectAutoCompleteTextView.getText().toString())) {
                    mPostButton.setEnabled(true);
                } else {
                    mPostButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private ArrayList<String> arraylistToStringArray(ArrayList<Goal> data) {
        ArrayList<String> arr = new ArrayList<>();
        for (Goal goal : data) {
            arr.add(goal.toString());
        }

        return arr;
    }
}
