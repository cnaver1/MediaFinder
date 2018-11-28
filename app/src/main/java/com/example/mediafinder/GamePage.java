package com.example.mediafinder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by thevs on 2/28/2018.
 * The Game Page uses the Game class to get the Game parameters from the database
 */

public class GamePage extends Fragment {

    ArrayList items = new ArrayList();

//Called to have the fragment instantiate its user interface view
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.games, container, false);
    }

//Called immediately after onCreateView; This gives subclasses a chance to initialize themselves once they know their view hierarchy has been completely created
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final SearchAdapter searchAdapter = new SearchAdapter(this.getContext(), R.id.gameList, items);
        ((ListView) view.findViewById(R.id.gameList)).setAdapter(searchAdapter);
        FirebaseDatabase.getInstance().getReference("Media").child("Games").orderByChild("lowerCaseName").addChildEventListener(new ChildEventListener() {
		//This method is triggered when a new child is added to the location to which this listener was added
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                searchAdapter.add(dataSnapshot.getValue(Game.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
