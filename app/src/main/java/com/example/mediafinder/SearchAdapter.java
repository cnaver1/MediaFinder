package com.example.mediafinder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by thevs on 4/3/2018.
 */

public class SearchAdapter extends ArrayAdapter {

    List list;
    Context context;

    public SearchAdapter(@NonNull Context context, int resource, List objects) {
        super(context, resource, objects);
        list = objects;
        this.context = context;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        if(view == null)
            view = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.search_list, null);
        ((Media) getItem(i)).setResults(view);
        view.findViewById(R.id.infoButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, MoreInfo.class));
                MoreInfo.media = (Media) getItem(i);
            }
        });
        return view;
    }
}
