package edu.umd.cs.sodorfriends2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;

import java.util.ArrayList;


public class TrainListActivity extends AppCompatActivity
        implements TrainListFragment.Callbacks {

    private SodorDB mDatabase;
    private ArrayList<Train> mTrains;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_app_bar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        mDatabase = new SodorDB(getApplicationContext());
        mTrains = mDatabase.getTrains();
    }

    public ArrayList<Train> getTrains() {
        return mTrains;
    }

    public void onItemSelected(String id) {
        // do nothing for now
    }
}
