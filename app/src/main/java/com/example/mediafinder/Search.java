package com.example.mediafinder;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by thevs on 2/28/2018.
 */

public class Search extends Fragment {

    final FirebaseDatabase db = FirebaseDatabase.getInstance();
    ArrayList list = new ArrayList();
    ListView lv;
    AutoCompleteTextView criteria;

//Called to have the fragment instantiate its user interface view
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search, container, false);
    }

//Called immediately after onCreateView; This gives subclasses a chance to initialize themselves once they know their view hierarchy has been completely created
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Context context = this.getContext();
        lv = view.findViewById(R.id.searchList);
        view.findViewById(R.id.background).setOnClickListener(new View.OnClickListener() {
		//Called when a view has been clicked.
            @Override
            public void onClick(View view) {
                ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
        final DatabaseReference ref = db.getReference("Media");
        final SearchAdapter searchAdapter = new SearchAdapter(this.getContext(), R.id.searchList, list);
        lv.setAdapter(searchAdapter);
        criteria = view.findViewById(R.id.criteria);

        //This is the code that searches for the media based off of the entered text
        view.findViewById(R.id.searchButton).setOnClickListener(new View.OnClickListener() {
		//Called when a view has been clicked.
            @Override
            public void onClick(View view) {
                searchAdapter.clear();
                final String search = criteria.getText().toString().trim();
                ref.child("Movies").orderByChild("title").startAt(search).endAt(search + "\uf8ff").addChildEventListener(new ChildEventListener() {
				//This method is triggered when a new child is added to the location to which this listener was added
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        searchAdapter.add(dataSnapshot.getValue(Movie.class));
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
                ref.child("Books").orderByChild("title").startAt(search).endAt(search + "\uf8ff").addChildEventListener(new ChildEventListener() {
				//This method is triggered when a new child is added to the location to which this listener was added
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        searchAdapter.add(dataSnapshot.getValue(Book.class));
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
                ref.child("Games").orderByChild("title").startAt(search).endAt(search + "\uf8ff").addChildEventListener(new ChildEventListener() {
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
        });
    }
}