package goals_keeper.com.goalskeeperapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.adapters.CreatePostAutoCompleteAdapter;

/**
 * Created by hamamsy on 28/11/15.
 */
public class CreatePostFragment extends android.support.v4.app.Fragment {

    @Bind(R.id.btn_cancel)
    Button mCancelButton;

    @Bind(R.id.auto_complete_goal_selection)
    AutoCompleteTextView mGoalSelectAutoCompleteTextView;

    CreatePostAutoCompleteAdapter mGoalsAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true); // To support modifying the toolbar menu on fragment change

        View view = inflater.inflate(R.layout.fragment_create_post, container, false); // Inflating the fragment layout

        ButterKnife.bind(this, view);

        ArrayList<String> data = new ArrayList<String>();
        data.add("Quit smoking");
        data.add("Getting A+ in theory of computation");
        data.add("Get driving license");
        data.add("Eat a banana");
        data.add("Win a marathon");

        mGoalsAdapter = new CreatePostAutoCompleteAdapter(getActivity(), data);

        mGoalSelectAutoCompleteTextView.setAdapter(mGoalsAdapter);

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        return view;
    }
}
