package vivu.com.vivuapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;

import vivu.com.vivuapp.R;
import vivu.com.vivuapp.adapter.ItemAdapter;
import vivu.com.vivuapp.asyn.LoadData;
import vivu.com.vivuapp.model.Item;
import vivu.com.vivuapp.views.DataRecycleV;

public class NewFeedFragment extends Fragment {
    DataRecycleV dataRecycleV;
    ItemAdapter itemAdapter;
    RecyclerView rv;
    private static final String newfeed = "http://vnexpress.net/rss/tin-moi-nhat.rss";
    private static final String newFeed2 = "http://vnexpress.net/rss/thoi-su.rss";
    public NewFeedFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_feed, container, false);
        rv = (RecyclerView) view.findViewById(R.id.rv_item);
        if (dataRecycleV.isConnected(getContext())){
            itemAdapter = new ItemAdapter(getContext(), new ArrayList<Item>());
            RecycleViewData(rv, itemAdapter);
            new LoadData(itemAdapter,getContext(), "newfeed").execute(newfeed, newFeed2);
        }else {
            itemAdapter = new ItemAdapter(getContext(), dataRecycleV.arrayListItem("newfeed", getContext()));
            RecycleViewData(rv, itemAdapter);
        }

        return view;
    }

    private void RecycleViewData(RecyclerView rv, ItemAdapter itemAdapters){

        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        rv.setAdapter(itemAdapters);
    }

}
