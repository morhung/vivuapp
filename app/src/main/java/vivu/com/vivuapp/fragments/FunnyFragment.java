package vivu.com.vivuapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import java.util.ArrayList;

import vivu.com.vivuapp.R;
import vivu.com.vivuapp.adapter.ItemAdapter;
import vivu.com.vivuapp.asyn.LoadData;
import vivu.com.vivuapp.model.Item;

import static android.content.ContentValues.TAG;


public class FunnyFragment extends Fragment {
    ItemAdapter itemAdapter;
    RecyclerView rv;

    private static final String funny = "http://www.24h.com.vn/upload/rss/cuoi24h.rss";
    private static final String funny2 = "http://vnexpress.net/rss/cuoi.rss";

    public FunnyFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_funny, container, false);
        rv = (RecyclerView) view.findViewById(R.id.rv_item);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        itemAdapter = new ItemAdapter(getContext(), new ArrayList<Item>());
        rv.setAdapter(itemAdapter);

        new LoadData(itemAdapter, getContext()).execute(funny, funny2);
        return view;

    }

}
