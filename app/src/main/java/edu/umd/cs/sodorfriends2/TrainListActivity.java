package edu.umd.cs.sodorfriends2;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class TrainListActivity extends AppCompatActivity {

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
        RecyclerView list = (RecyclerView) findViewById(R.id.trains_list);
        list.setAdapter(new TrainRecyclerViewAdapter());

    }

    public ArrayList<Train> getTrains() {
        return mTrains;
    }

    public class TrainRecyclerViewAdapter
            extends RecyclerView.Adapter<TrainRecyclerViewAdapter.ViewHolder> {


        public TrainRecyclerViewAdapter() {
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.train_list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            final Train train = mTrains.get(position);

            holder.getImageView().setImageBitmap(train.getBitmap());
            holder.getNameView().setText(train.getName());
            holder.getNumView().setText(train.getNum());
        }

        @Override
        public int getItemCount() {
            return mTrains.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder {

            private final ImageView mImage;
            private final TextView mName;
            private final TextView mNumber;


            public ViewHolder(View itemView) {
                super(itemView);

                mImage = (ImageView) itemView.findViewById(R.id.train_image);
                mName = (TextView) itemView.findViewById(R.id.train_name);
                mNumber = (TextView) itemView.findViewById(R.id.train_num);
            }

            @Override
            public String toString() {
                String toreturn = super.toString() + " '" + mName.getText() + "'";
                return toreturn;
            }

            public ImageView getImageView() { return  mImage;}
            public TextView getNameView() { return mName; }
            public TextView getNumView() { return mNumber; }

        }

    }
}