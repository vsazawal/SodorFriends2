package edu.umd.cs.sodorfriends2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
  * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class TrainListFragment extends ListFragment {


    /**
     * The fragment's current callback object, which is notified of list item
     * clicks.
     */
    private Callbacks mCallbacks;


    public interface Callbacks {
        /**
         * Callback for when an item has been selected.
         */
        public void onItemSelected(String id);
    }

    private List<Train> mytrains;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TrainListFragment() {


    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mytrains = new ArrayList<Train>();

        /*mytrains.add(new Train("Thomas"));
        mytrains.add(new Train("Edward"));
        mytrains.add(new Train("Percy"));*/
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TrainListActivity activity = (TrainListActivity)getActivity();
        mytrains = activity.getTrains();
               setListAdapter(new ArrayAdapter<Train>(
                getActivity(),
                R.layout.train_list_item,
                R.id.train_name,
                mytrains));

    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        // Notify the active callbacks interface (the activity, if the
        // fragment is attached to one) that an item has been selected.
        TextView list_item = (TextView)view;
        mCallbacks.onItemSelected(list_item.getText().toString());
    }


}
