package goals_keeper.com.goalskeeperapp.utils;

import android.content.Context;

import com.facebook.AccessToken;

/**
 * Created by kady on 01/12/15.
 *
 * @author kady
 */
public class Utilities {

    /**
     * Checking whether user is logged in with facebook or not
     * @param context
     * @return true if user is logged in, false otherwise
     */
    public static boolean isLoggedIn(Context context) {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null && !accessToken.isExpired() && context.getSharedPreferences(Constants.SHARED_PREFS_KEY, Context.MODE_PRIVATE).getString(Constants.FACEBOOK_TOKEN, null) != null;
    }
}
