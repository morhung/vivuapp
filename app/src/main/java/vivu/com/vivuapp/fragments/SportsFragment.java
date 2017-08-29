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

public class SportsFragment extends Fragment {

    ItemAdapter itemAdapter;
    RecyclerView rv;
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
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        itemAdapter = new ItemAdapter(getContext(), new ArrayList<Item>());
        rv.setAdapter(itemAdapter);
        new LoadData(itemAdapter, getContext()).execute(bongda);
        return view;

    }

}
