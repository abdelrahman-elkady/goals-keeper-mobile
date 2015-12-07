package goals_keeper.com.goalskeeperapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.utils.Constants;

/**
 * Created by kady on 23/11/15.
 *
 * @author kady
 */
public class LoginFragment extends android.support.v4.app.Fragment {

    @Bind(R.id.fragment_login_button_facebook_login)
    LoginButton mFacebookLoginButton;


    CallbackManager mFacebookCallback;

    SharedPreferences mSharedPreferences;
    AccessToken mToken;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true); // To support modifying the toolbar menu on fragment change

        final View view = inflater.inflate(R.layout.fragment_login, container, false); // Inflating the fragment layout

        ButterKnife.bind(this, view);

        mSharedPreferences = getActivity().getSharedPreferences(Constants.SHARED_PREFS_KEY, Context.MODE_PRIVATE);

        mFacebookCallback = CallbackManager.Factory.create();

        settingLoginPermissions();
        mFacebookLoginButton.setFragment(this);

        handleLoginCallback();

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mFacebookCallback.onActivityResult(requestCode, resultCode, data);
    }

    private void handleLoginCallback() {
        mFacebookLoginButton.registerCallback(mFacebookCallback, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                mToken = loginResult.getAccessToken();
                Log.d("TOKEN", mToken.getToken());
                mSharedPreferences.edit().putString(Constants.FACEBOOK_TOKEN, mToken.getToken()).apply();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    private void settingLoginPermissions() {
        mFacebookLoginButton.setReadPermissions("user_friends");
//        mFacebookLoginButton.setPublishPermissions("publish_actions");
    }

    @OnClick(R.id.fragment_login_button_facebook_login)
    public void loginWithFacebook() {

//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        HomeFragment homeFragment = new HomeFragment();
//        fragmentTransaction.replace(R.id.fragment_container, homeFragment);
//        fragmentTransaction.commit();


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear(); // No menu on login screen !
    }
}


