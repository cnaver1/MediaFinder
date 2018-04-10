package com.example.mediafinder;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by thevs on 4/3/2018.
 */

public class SearchAdapter extends ArrayAdapter {

    List list;

    public SearchAdapter(@NonNull Context context, int resource, List objects) {
        super(context, resource, objects);
        list = objects;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        if(view == null)
            view = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.search_list, null);
        ((TextView) view.findViewById(R.id.resultsText)).setText(((Media) list.get(i)).getDef());
        return view;
    }

}
