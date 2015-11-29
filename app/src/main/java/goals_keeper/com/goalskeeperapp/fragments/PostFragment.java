package goals_keeper.com.goalskeeperapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import goals_keeper.com.goalskeeperapp.R;

/**
 * Created by hamamsy on 28/11/15.
 */
public class PostFragment extends android.support.v4.app.Fragment {

    @Bind(R.id.btn_cancel)
    Button mCancelButtom;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true); // To support modifying the toolbar menu on fragment change

        View view = inflater.inflate(R.layout.fragment_post, container, false); // Inflating the fragment layout

        ButterKnife.bind(this, view);
        mCancelButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                HomeFragment homeFragment = new HomeFragment();
                fragmentTransaction.replace(R.id.main_fragment_container, homeFragment).addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return view;
    }
}
