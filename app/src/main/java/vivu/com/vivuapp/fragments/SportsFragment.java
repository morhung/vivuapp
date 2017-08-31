package vivu.com.vivuapp.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import io.realm.Realm;
import vivu.com.vivuapp.R;
import vivu.com.vivuapp.adapter.ItemAdapter;
import vivu.com.vivuapp.asyn.LoadData;
import vivu.com.vivuapp.model.Item;
import vivu.com.vivuapp.views.DataRecycleV;

import static vivu.com.vivuapp.views.DataRecycleV.arrayListItem;

public class SportsFragment extends Fragment {

    ItemAdapter itemAdapter;
    RecyclerView rv;
    Realm realmDb;
    Context mContext;
    private static final String bongda = "http://www.24h.com.vn/upload/rss/bongda.rss";

    public SportsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_sports, container, false);
        rv = (RecyclerView) view.findViewById(R.id.rv_item);
        if (DataRecycleV.isConnected(getContext())){
            itemAdapter = new ItemAdapter(getContext(), new ArrayList<Item>());
            RecycleViewData(rv, itemAdapter);
            new LoadData(itemAdapter, getContext(), "sports").execute(bongda);
        }else {
            itemAdapter = new ItemAdapter(getContext(), arrayListItem("sports", getContext()));
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
