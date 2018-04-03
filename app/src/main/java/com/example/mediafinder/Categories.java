package com.example.mediafinder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by thevs on 2/28/2018.
 */

public class Categories extends Fragment {

    String[] categories = {"Cat 1", "Cat 2", "Cat 3", "Cat 4", "Cat 5"};
    String[] items = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.categories, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((ListView) view.findViewById(R.id.catList)).setAdapter(new CatAdapter(this.getContext(), 0, categories, this));
    }
}
