package com.example.mediafinder;

import android.content.Context;
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

import java.util.ArrayList;

/**
 * Created by thevs on 2/28/2018.
 */

public class Search extends Fragment {

    final FirebaseDatabase db = FirebaseDatabase.getInstance();
    ArrayList list = new ArrayList();
    ListView lv;
    AutoCompleteTextView criteria;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Context context = this.getContext();
        lv = view.findViewById(R.id.searchList);
        view.findViewById(R.id.background).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
        });
        db.getReference("media").child("1");
        DatabaseReference ref = db.getReference("media");
        ref.removeValue();
        ref.push().setValue(new Media("1", "This is the first one"));
        ref.push().setValue(new Media("2", "This is the second one"));
        ref.push().setValue(new Media("3", "This is the third one"));
        final SearchAdapter searchAdapter = new SearchAdapter(this.getContext(), R.id.searchList, list);
        lv.setAdapter(searchAdapter);
        criteria = view.findViewById(R.id.criteria);
        view.findViewById(R.id.searchButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchAdapter.clear();
                db.getReference("media").addChildEventListener(new ChildEventListener() {

                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        if(dataSnapshot.getValue(Media.class).getName().equals(criteria.getText().toString()))
                            searchAdapter.add(dataSnapshot.getValue(Media.class));
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
