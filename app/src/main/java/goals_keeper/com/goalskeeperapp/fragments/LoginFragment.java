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
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.api.Api;
import goals_keeper.com.goalskeeperapp.models.Token;
import goals_keeper.com.goalskeeperapp.models.User;
import goals_keeper.com.goalskeeperapp.utils.Constants;
import goals_keeper.com.goalskeeperapp.utils.Utilities;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

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

        showTimeline();

        mFacebookCallback = CallbackManager.Factory.create();

        settingLoginPermissions();
        mFacebookLoginButton.setFragment(this);

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mFacebookCallback.onActivityResult(requestCode, resultCode, data);
    }


    @OnClick(R.id.fragment_login_button_facebook_login)
    public void loginWithFacebook() {

        mFacebookLoginButton.registerCallback(mFacebookCallback, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                mToken = loginResult.getAccessToken();
                Log.d("FACEBOOK TOKEN", mToken.getToken());

                mSharedPreferences.edit().putString(Constants.FACEBOOK_TOKEN, mToken.getToken()).apply(); // Saving the token in shared prefs.

                showTimeline();

                Call<User> request = Api.publicRoutes(getActivity()).authenticate(new Token(mToken.getToken()));
                request.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Response<User> response, Retrofit retrofit) {
                        User user = response.body();
                        mSharedPreferences.edit().putInt(Constants.USER_ID, user.getId()).apply();
                        Log.d("AUTH", "Saved ID: " + mSharedPreferences.getInt(Constants.USER_ID, -1));
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(getActivity(), "Failed to communicate with goals keeper server", Toast.LENGTH_LONG).show();
                        Log.e("AUTH", t.getMessage());
                    }
                });


            }

            @Override
            public void onCancel() {
                Log.d("FACEBOOK", "Login cancelled");
            }

            @Override
            public void onError(FacebookException error) {
                Log.e("FACEBOOK", "Login failed " + error.getMessage());
            }
        });

    }


    private void settingLoginPermissions() {
        mFacebookLoginButton.setReadPermissions(Arrays.asList("user_friends", "user_hometown", "user_birthday"));
    }

    private void showTimeline() {
        if (Utilities.isLoggedIn(getActivity())) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            HomeFragment homeFragment = new HomeFragment();
            fragmentTransaction.replace(R.id.fragment_container, homeFragment);
            fragmentTransaction.commit();
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear(); // No menu on login screen !
    }
}


