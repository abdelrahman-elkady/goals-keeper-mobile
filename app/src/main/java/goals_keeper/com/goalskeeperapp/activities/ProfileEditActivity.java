package goals_keeper.com.goalskeeperapp.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.activities.base.BaseActivity;
import goals_keeper.com.goalskeeperapp.api.Api;
import goals_keeper.com.goalskeeperapp.models.User;
import goals_keeper.com.goalskeeperapp.utils.Constants;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by kady on 06/12/15.
 *
 * @author kady
 */
public class ProfileEditActivity extends BaseActivity {
    @Bind(R.id.activity_profile_edit_button_save)
    Button mSaveButton;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.activity_profile_edit_image_view_profile)
    ImageView mEditImageView;

    @Bind(R.id.activity_profile_edit_edit_text_first_name)
    TextView editTextFirstName;

    @Bind(R.id.activity_profile_edit_edit_text_last_name)
    TextView editTextLastName;

    @Bind(R.id.activity_profile_edit_edit_text_city)
    TextView editTextCity;

    @Bind(R.id.activity_profile_edit_edit_text_country)
    TextView editTextCountry;


    SharedPreferences mSharedPreferences;
    User mUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        ButterKnife.bind(this);

        mToolbar.setTitle("Edit your info");
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mSharedPreferences = getSharedPreferences(Constants.SHARED_PREFS_KEY, Context.MODE_PRIVATE);

        int userId = mSharedPreferences.getInt(Constants.USER_ID, -1);

        Api.privateRoutes(this).showUser(userId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response, Retrofit retrofit) {
                mUser = response.body();

                Picasso.with(ProfileEditActivity.this).load(mUser.getProfilePicture()).into(mEditImageView);
                editTextFirstName.setText(mUser.getFirstName());
                editTextLastName.setText(mUser.getLastName());
                editTextCity.setText(mUser.getCity());
                editTextCountry.setText(mUser.getCountry());
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(ProfileEditActivity.this, "Failed to retrieve user's data", Toast.LENGTH_SHORT).show();
                Log.e("USER", t.getMessage());
            }
        });

    }

    @OnClick(R.id.activity_profile_edit_button_save)
    public void saveProfileEdit() {
        if (mUser != null) {

            mUser.setFirstName(editTextFirstName.getText().toString());
            mUser.setLastName(editTextLastName.getText().toString());
            mUser.setCity(editTextCity.getText().toString());
            mUser.setCountry(editTextCountry.getText().toString());

            int userId = mSharedPreferences.getInt(Constants.USER_ID, -1);

            Api.privateRoutes(this).editUser(userId, mUser).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Response<Void> response, Retrofit retrofit) {
                    if (response.code() == 200) {
                        Toast.makeText(ProfileEditActivity.this, "Profile edited successfully", Toast.LENGTH_LONG).show();
                        onBackPressed();
                    } else {
                        Toast.makeText(ProfileEditActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        Log.e("Edit Profile", response.code() + ": " + response.message());
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(ProfileEditActivity.this, "Failed to edit profile", Toast.LENGTH_SHORT).show();
                    Log.e("Edit Profile", t.getMessage());
                }
            });
        }
        onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_default, menu);
        return true;
    }


}
