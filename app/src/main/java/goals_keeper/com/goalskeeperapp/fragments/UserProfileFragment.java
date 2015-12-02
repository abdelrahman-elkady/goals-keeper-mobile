package goals_keeper.com.goalskeeperapp.fragments;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import goals_keeper.com.goalskeeperapp.R;

/**
 * Created by kady on 02/12/15.
 *
 * @author kady
 */
public class UserProfileFragment extends Fragment {

    @Bind(R.id.imgbtn_follow)
    ImageButton mFollowImageButton;

    @Bind(R.id.name_text)
    TextView mUsernameTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        ButterKnife.bind(this, view);

        followUser();
        mUsernameTextView.setText(getArguments().getString("USER_NAME"));

        return view;
    }

    private void followUser() {
        mFollowImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setActivated(!v.isActivated());
                //TODO: Implement following user logic
            }
        });
    }

}