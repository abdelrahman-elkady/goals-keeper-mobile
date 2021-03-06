package goals_keeper.com.goalskeeperapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.activities.GoalsListingActivity;
import goals_keeper.com.goalskeeperapp.api.Api;
import goals_keeper.com.goalskeeperapp.models.User;
import goals_keeper.com.goalskeeperapp.utils.Constants;
import goals_keeper.com.goalskeeperapp.utils.Helpers;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by kady on 02/12/15.
 *
 * @author kady
 */
public class UserProfileFragment extends Fragment {

    @Bind(R.id.imgbtn_follow)
    ImageButton mFollowImageButton;

    @Bind(R.id.person_timeline_btn)
    Button mPersonTimelineButton;

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

    @Bind(R.id.fragment_user_profile_button_goals)
    Button mGoalsButton;

    Bundle mBundle;
    User mUser;

    SharedPreferences mSharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        setHasOptionsMenu(true); // To support modifying the toolbar menu on fragment change
        ButterKnife.bind(this, view);

        mBundle = getArguments();
        mSharedPreferences = getActivity().getSharedPreferences(Constants.SHARED_PREFS_KEY, Context.MODE_PRIVATE);
        fetchUserData();

        followUser();

        Helpers.setToolbarTitle((AppCompatActivity) getActivity(), getArguments().getString(Constants.TOOLBAR_TITLE));

        mPersonTimelineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Bundle bundle = new Bundle();
                bundle.putString(Constants.TOOLBAR_TITLE, getArguments().getString(Constants.BUNDLE_USER_NAME) + "'s Timeline");
                bundle.putInt(Constants.TIMELINE_MODE, Constants.USER_TIMELINE);
                bundle.putInt(Constants.BUNDLE_USER_ID, mBundle.getInt(Constants.BUNDLE_USER_ID, -1));

                TimelineFragment timelineFragment = new TimelineFragment();
                timelineFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container, timelineFragment).addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        mGoalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goalsListingIntent = new Intent(getActivity(), GoalsListingActivity.class);
                startActivity(goalsListingIntent);
            }
        });

        return view;
    }

    private void fetchUserData() {
        Api.privateRoutes(getActivity()).showUser(mBundle.getInt(Constants.BUNDLE_USER_ID, -1)).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response, Retrofit retrofit) {
                mUser = response.body();

                mNameTextView.setText(mUser.getName());

                if (mUser.getCity() == null || mUser.getCity().isEmpty()) {
                    mCityTextView.setVisibility(View.GONE);
                } else {
                    mCityTextView.setVisibility(View.VISIBLE);
                    mCityTextView.setText(mUser.getCity());
                }

                if (mUser.getCountry() == null || mUser.getCountry().isEmpty()) {
                    mCountryTextView.setVisibility(View.GONE);
                } else {
                    mCountryTextView.setVisibility(View.VISIBLE);
                    mCountryTextView.setText(mUser.getCountry());
                }

                if (mUser.getGender() == null) {
                    mGenderTextView.setVisibility(View.GONE);
                } else {
                    mGenderTextView.setVisibility(View.VISIBLE);
                    mGenderTextView.setText(mUser.getGender() ? "Male" : "Female");
                }

                if (mUser.getDateOfBirth() == null || mUser.getDateOfBirth().isEmpty()) {
                    mAgeTextView.setVisibility(View.GONE);
                } else {
                    mAgeTextView.setVisibility(View.VISIBLE);
                    mAgeTextView.setText(mUser.getDateOfBirth());
                }

                Api.privateRoutes(getActivity()).listFollowings(mSharedPreferences.getInt(Constants.USER_ID, -1)).enqueue(new Callback<ArrayList<User>>() {
                    @Override
                    public void onResponse(Response<ArrayList<User>> response, Retrofit retrofit) {
                        if (response.code() == 200) {
                            ArrayList<User> followings = response.body();
                            if (followings != null)
                                mFollowImageButton.setActivated(followings.contains(mUser));
                        } else {
                            Toast.makeText(getActivity(), "Failed to fetch user following status", Toast.LENGTH_SHORT).show();
                            Log.e("USER PROFILE", response.code() + " : " + response.message());
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });


            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getActivity(), "Failed to fetch user data", Toast.LENGTH_SHORT).show();
                Log.e("USER PROFILE", t.getMessage());
            }
        });


    }

    private void followUser() {
        mFollowImageButton.setOnClickListener(new View.OnClickListener() {
            int userId = mSharedPreferences.getInt(Constants.USER_ID, -1);
            int followedId = mBundle.getInt(Constants.BUNDLE_USER_ID, -1);

            @Override
            public void onClick(final View v) {
                if (v.isActivated()) {
                    //Unfollow User
                    Api.privateRoutes(getActivity()).unFollowUser(userId, followedId).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Response<Void> response, Retrofit retrofit) {
                            if (response.code() == 200) {
                                Toast.makeText(getActivity(), "UnFollowed User Successfully", Toast.LENGTH_LONG).show();
                                v.setActivated(false);
                            } else {
                                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                                Log.e("UNFOLLOW USER", response.code() + ": " + response.message());
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            Toast.makeText(getActivity(), "Failed to unfollow user", Toast.LENGTH_SHORT).show();
                            Log.e("UNFOLLOW USER", t.getMessage());
                        }
                    });
                } else {
                    //Follow User
                    Api.privateRoutes(getActivity()).followUser(userId, followedId).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Response<Void> response, Retrofit retrofit) {
                            if (response.code() == 200) {
                                Toast.makeText(getActivity(), "Followed User Successfully", Toast.LENGTH_LONG).show();
                                v.setActivated(true);
                            } else {
                                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                                Log.e("FOLLOW USER", response.code() + ": " + response.message());
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            Toast.makeText(getActivity(), "Failed to follow user", Toast.LENGTH_SHORT).show();
                            Log.e("FOLLOW USER", t.getMessage());
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        menu.clear();
        inflater.inflate(R.menu.menu_default, menu);

    }

}
