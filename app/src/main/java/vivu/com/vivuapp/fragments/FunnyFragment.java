package vivu.com.vivuapp.fragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import vivu.com.vivuapp.R;
import vivu.com.vivuapp.adapter.ItemAdapter;
import vivu.com.vivuapp.asyn.LoadData;
import vivu.com.vivuapp.model.Item;

import static android.content.ContentValues.TAG;
import static vivu.com.vivuapp.views.DataRecycleV.arrayListItem;
import static vivu.com.vivuapp.views.DataRecycleV.isConnected;


public class FunnyFragment extends Fragment {
    ItemAdapter itemAdapter;
    RecyclerView rv;
    Context mContext;
    Realm realmDb;
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
        if (isConnected(getContext())){
            itemAdapter = new ItemAdapter(getContext(), new ArrayList<Item>());
            RecycleViewData(rv, itemAdapter);
            new LoadData(itemAdapter, getContext(), "funny").execute(funny, funny2);
        }else {

            itemAdapter = new ItemAdapter(getContext(), arrayListItem("funny", getContext()));
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

//    //Read data from realm database.
//    private ArrayList<Item> arrayListItem(String theloai){
//        Realm.init(mContext);
//        realmDb =Realm.getDefaultInstance();
//        ArrayList<Item> listItem = new ArrayList<>(realmDb.where(Item.class).equalTo("theloai", theloai).findAll());
//        return listItem;
//    }
//
////    private RealmResults<Item> getItemArrayList(){
////        Realm.init(mContext);
////        realmDb = Realm.getDefaultInstance();
////        RealmResults<Item> listItem = realmDb.where(Item.class).findAll();
////        return listItem;
////    }
//
//    //Check internet
//    public boolean isConnected() {
//        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo netInfo = cm.getActiveNetworkInfo();
//        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
//            return true;
//        }
//        return false;
//    }

}
