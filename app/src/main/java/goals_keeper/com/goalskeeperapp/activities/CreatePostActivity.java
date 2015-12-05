package goals_keeper.com.goalskeeperapp.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.adapters.CreatePostAutoCompleteAdapter;

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

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    CreatePostAutoCompleteAdapter mGoalsAdapter;
    ArrayList<String> mData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        ButterKnife.bind(this);

        mToolbar.setTitle("New Post");
        setSupportActionBar(mToolbar);

        mData = new ArrayList<>();
        initData();

        mGoalsAdapter = new CreatePostAutoCompleteAdapter(this, mData);
        mGoalSelectAutoCompleteTextView.setAdapter(mGoalsAdapter);

        validateInput();

    }

    @OnClick(R.id.activity_create_post_button_cancel)
    public void navigateBack() {
        onBackPressed();
    }

    private void initData() {
        mData.add("Quit smoking");
        mData.add("Getting A+ in theory of computation");
        mData.add("Get driving license");
        mData.add("Eat a banana");
        mData.add("Win a marathon");
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
                if (mData.contains(mGoalSelectAutoCompleteTextView.getText().toString())) {
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
}
