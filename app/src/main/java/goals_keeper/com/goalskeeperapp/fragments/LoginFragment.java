package goals_keeper.com.goalskeeperapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.activities.UserConnectionsActivity;
import goals_keeper.com.goalskeeperapp.utils.Constants;

/**
 * Created by kady on 23/11/15.
 *
 * @author kady
 */
public class LoginFragment extends android.support.v4.app.Fragment {

    @Bind(R.id.btn_facebook_login)
    Button mFacebookLoginButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true); // To support modifying the toolbar menu on fragment change

        final View view = inflater.inflate(R.layout.fragment_login, container, false); // Inflating the fragment layout

        ButterKnife.bind(this, view);
        
        return view;
    }

    @OnClick(R.id.btn_facebook_login)
    public void loginWithFacebook() {

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        HomeFragment homeFragment = new HomeFragment();
        fragmentTransaction.replace(R.id.main_fragment_container, homeFragment);
        fragmentTransaction.commit();



    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear(); // No menu on login screen !
    }
}


