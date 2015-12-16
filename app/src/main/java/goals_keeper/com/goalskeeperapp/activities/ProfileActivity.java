package goals_keeper.com.goalskeeperapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.activities.base.BaseActivity;
import goals_keeper.com.goalskeeperapp.api.Api;
import goals_keeper.com.goalskeeperapp.models.User;
import goals_keeper.com.goalskeeperapp.utils.Constants;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ProfileActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.fab_profile_edit)
    FloatingActionButton mProfileEditFAB;

    @Bind(R.id.activity_profile_image_view_profile)
    ImageView mProfilePictureImageView;

    @Bind(R.id.activity_profile_text_view_name)
    TextView mNameTextView;

    @Bind(R.id.activity_profile_text_view_city)
    TextView mCityTextView;

    @Bind(R.id.activity_profile_text_view_country)
    TextView mCountryTextView;

    @Bind(R.id.activity_profile_text_view_gender)
    TextView mGenderTextView;

    @Bind(R.id.activity_profile_text_view_age)
    TextView mAgeTextView;

    @Bind(R.id.activity_profile_button_followers)
    Button mFollowersButton;

    @Bind(R.id.activity_profile_button_following)
    Button mFollowingButton;

    @Bind(R.id.appbar)
    AppBarLayout mAppBarLayout;

    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);

        mToolbar.setTitle("Your Profile");
        setSupportActionBar(mToolbar);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mSharedPreferences = getSharedPreferences(Constants.SHARED_PREFS_KEY, MODE_PRIVATE);

        hideFabOnScroll();

    }

    @Override
    protected void onResume() {
        super.onResume();


        Api.privateRoutes(this).showUser(mSharedPreferences.getInt(Constants.USER_ID, -1)).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response, Retrofit retrofit) {
                User me = response.body();

                if (me.getProfilePicture() != null) {
                    Picasso.with(ProfileActivity.this).load(me.getProfilePicture()).into(mProfilePictureImageView);
                }

                mNameTextView.setText(me.getName());

                if (me.getCity() == null || me.getCity().isEmpty()) {
                    mCityTextView.setVisibility(View.GONE);
                } else {
                    mCityTextView.setVisibility(View.VISIBLE);
                    mCityTextView.setText(me.getCity());
                }

                if (me.getCountry() == null || me.getCountry().isEmpty()) {
                    mCountryTextView.setVisibility(View.GONE);
                } else {
                    mCountryTextView.setVisibility(View.VISIBLE);
                    mCountryTextView.setText(me.getCountry());
                }

                if (me.getGender() == null) {
                    mGenderTextView.setVisibility(View.GONE);
                } else {
                    mGenderTextView.setVisibility(View.VISIBLE);
                    mGenderTextView.setText(me.getGender() ? "Male" : "Female");
                }

                if (me.getDateOfBirth() == null || me.getDateOfBirth().isEmpty()) {
                    mAgeTextView.setVisibility(View.GONE);
                } else {
                    mAgeTextView.setVisibility(View.VISIBLE);
                    mAgeTextView.setText(me.getDateOfBirth());
                }

            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(ProfileActivity.this, "Failed to get your data", Toast.LENGTH_SHORT).show();
                Log.e("MY PROFILE", t.getMessage());
            }
        });
    }

    @OnClick(R.id.fab_profile_edit)
    public void editProfile() {
        Intent editProfileIntent = new Intent(this, ProfileEditActivity.class);
        startActivity(editProfileIntent);
    }

    @OnClick(R.id.activity_profile_button_following)
    public void showFollowing() {
        Intent followingIntent = new Intent(this, UserConnectionsActivity.class);

        ArrayList<String> data = new ArrayList<String>();
        data.add("Ali Hassan");
        data.add("Abdelrahman Elkady");
        data.add("Mohammed Mostafa");
        data.add("Ahmed Saleh");

        followingIntent.putStringArrayListExtra(Constants.BUNDLE_USERS_KEY, data);
        followingIntent.putExtra(Constants.USER_CONNECTION_TYPE, Constants.FOLLOWINGS);
        startActivity(followingIntent);
    }

    @OnClick(R.id.activity_profile_button_followers)
    public void showFollowers() {
        Intent followersIntent = new Intent(this, UserConnectionsActivity.class);

        ArrayList<String> data = new ArrayList<String>();
        data.add("Ali Hassan");
        data.add("Ahmed Saleh");

        followersIntent.putStringArrayListExtra(Constants.BUNDLE_USERS_KEY, data);
        followersIntent.putExtra(Constants.USER_CONNECTION_TYPE, Constants.FOLLOWERS);
        startActivity(followersIntent);
    }

    private void hideFabOnScroll() {
        // Thanks to http://stackoverflow.com/a/33145487/3357910
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (appBarLayout.getHeight() / 2 < -verticalOffset) {
                    mProfileEditFAB.animate().alpha(0.0f);
                    mProfileEditFAB.setVisibility(View.GONE);
                } else {
                    mProfileEditFAB.setVisibility(View.VISIBLE);
                    mProfileEditFAB.animate().alpha(1.0f);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_search:
                Intent searchIntent = new Intent(this, SearchActivity.class);
                startActivity(searchIntent);
                return true;
            case R.id.action_about:
                Intent aboutIntent = new Intent(this, AboutActivity.class);
                startActivity(aboutIntent);
                return true;
            case R.id.action_logout:
                logout();
                Intent mainIntent = new Intent(this,MainActivity.class);
                startActivity(mainIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    public void logout(){
        SharedPreferences mSharedPreferences = getSharedPreferences(Constants.SHARED_PREFS_KEY, Context.MODE_PRIVATE);
        mSharedPreferences.edit().remove(Constants.USER_ID).apply();
        mSharedPreferences.edit().remove(Constants.FACEBOOK_TOKEN).apply();
        LoginManager.getInstance().logOut();
    }
}
