package goals_keeper.com.goalskeeperapp.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by kady on 01/12/15.
 *
 * @author kady
 */
public class Helpers {

    /**
     * Set the actionbar title of a given AppCompatActivity
     *
     * @param activity
     * @param title
     */
    public static void setToolbarTitle(AppCompatActivity activity, String title) {
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setTitle(title);
        } else {
            Log.e("GOALS_KEEPER", "NullPointerException while getting SupportActionBar, make sure you set SupportActionBar in your activity");
        }
    }

    /**
     * Set the actionbar title of a given activity
     *
     * @param activity
     * @param title
     */
    public static void setToolbarTitle(Activity activity, String title) {
        if (activity.getActionBar() != null) {
            activity.getActionBar().setTitle(title);
        } else {
            Log.e("GOALS_KEEPER", "NullPointerException while getting ActionBar, make sure you set ActionBar in your activity");
        }
    }
}
