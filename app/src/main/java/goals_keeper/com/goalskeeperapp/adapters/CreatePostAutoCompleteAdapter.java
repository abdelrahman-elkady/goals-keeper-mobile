package goals_keeper.com.goalskeeperapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.models.Goal;

/**
 * Created by abdelrahman on 29/11/15.
 * <p/>
 * TODO: check if we need the custom adapter or it's sufficient to use the arrayadapter directly
 */
public class CreatePostAutoCompleteAdapter extends ArrayAdapter<Goal> {

    public CreatePostAutoCompleteAdapter(Context context, ArrayList<Goal> data) {
        this(context, android.R.layout.simple_list_item_1, data);
    }

    public CreatePostAutoCompleteAdapter(Context context, int resource, List<Goal> objects) {
        super(context, resource, objects);
    }

}

