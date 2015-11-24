package goals_keeper.com.goalskeeperapp.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.activities.ProfileActivity;

/**
 * Created by kady on 23/11/15.
 */
public class LoginFragment extends Fragment {

    @Bind(R.id.btn_facebook_login)
    Button mFacebookLoginButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_login, container, false); // Inflating the fragment layout

        ButterKnife.bind(this, view);

        mFacebookLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(view, "Welcome Back, Get a Snack !", Snackbar.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
