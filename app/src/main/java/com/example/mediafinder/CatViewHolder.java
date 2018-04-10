package com.example.mediafinder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by thevs on 3/2/2018.
 */

public class CatViewHolder extends RecyclerView.ViewHolder {
    public ImageButton imageButton;

    public CatViewHolder(View view){
        super(view);
        imageButton = (ImageButton) view.findViewById(R.id.cat_item_button);
    }
}
