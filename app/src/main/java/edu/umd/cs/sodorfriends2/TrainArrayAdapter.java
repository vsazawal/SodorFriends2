package edu.umd.cs.sodorfriends2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Vibha on 4/12/2016.
 */
public class TrainArrayAdapter extends ArrayAdapter<Train> {

    List<Train> mTrains;


    private static class ViewHolder {

        private ImageView image;
        private TextView name;
        private TextView number;
        
        public void setViews(View parent, Train train) {
            image = (ImageView) parent.findViewById(R.id.train_image);
            name = (TextView) parent.findViewById(R.id.train_name);
            number = (TextView) parent.findViewById(R.id.train_num);
            name.setText(train.getName());
            number.setText(train.getNum());
            image.setImageBitmap(train.getBitmap());


        }
    }

    public TrainArrayAdapter(Context context, List<Train> trains) {
        super(context, -1, trains);
        mTrains = trains;
        Log.d("TRAINARRAYADAPTER", "in constructor");
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Train train = mTrains.get(position);
        ViewHolder viewHolder = new ViewHolder();
        // do not reuse convertview for now
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.train_list_item, parent, false);

        viewHolder.setViews(convertView, train);
        return convertView;
    }

    public int getCount() {
        return mTrains.size();
    }

}
