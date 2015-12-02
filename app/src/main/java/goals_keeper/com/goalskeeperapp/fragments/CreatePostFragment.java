package goals_keeper.com.goalskeeperapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.adapters.CreatePostAutoCompleteAdapter;
import goals_keeper.com.goalskeeperapp.utils.Helpers;

/**
 * Created by hamamsy on 28/11/15.
 */
public class CreatePostFragment extends android.support.v4.app.Fragment {

    @Bind(R.id.btn_cancel)
    Button mCancelButton;

    @Bind(R.id.auto_complete_goal_selection)
    AutoCompleteTextView mGoalSelectAutoCompleteTextView;

    @Bind(R.id.btn_post)
    Button mPostButton;

    CreatePostAutoCompleteAdapter mGoalsAdapter;
    ArrayList<String> mData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true); // To support modifying the toolbar menu on fragment change

        View view = inflater.inflate(R.layout.fragment_create_post, container, false); // Inflating the fragment layout

        ButterKnife.bind(this, view);
        Helpers.setToolbarTitle((AppCompatActivity) getActivity(), "New Post");

        mData = new ArrayList<>();
        initData();

        mGoalsAdapter = new CreatePostAutoCompleteAdapter(getActivity(), mData);
        mGoalSelectAutoCompleteTextView.setAdapter(mGoalsAdapter);

        validateInput();

        return view;
    }

    @OnClick(R.id.btn_cancel)
    public void navigateBack() {
        getActivity().onBackPressed();
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
