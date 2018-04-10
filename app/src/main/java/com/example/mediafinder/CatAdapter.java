package com.example.mediafinder;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thevs on 3/1/2018.
 */

public class CatAdapter extends ArrayAdapter {
    Categories categories;
    String[] items;
    ArrayList list;

    public CatAdapter(Context context, int resource, Object[] objects, Categories categories, ArrayList<String> list) {
        super(context, resource, objects);
        items = (String[]) objects;
        this.categories = categories;
        this.list = list;
    }

    public View getView(final int i, View view, ViewGroup viewGroup){
        if(view == null)
            view = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.category_list_item, null);
        ((TextView) view.findViewById(R.id.catName)).setText(items[i]);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.sideScroll);
        LinearLayoutManager layoutManager = new LinearLayoutManager(categories.getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //if(recyclerView != null)
        recyclerView.setAdapter(new CatItemsAdapter(list));
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }
}
