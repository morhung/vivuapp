package vivu.com.vivuapp.views;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import io.realm.Realm;
import vivu.com.vivuapp.adapter.ItemAdapter;
import vivu.com.vivuapp.model.Item;

import static java.security.AccessController.getContext;

/**
 * Created by TumPc on 8/31/2017.
 */

public class DataRecycleV {
   // private static AccessibilityService mContext;
    static Realm realmDb;
    //Read data from realm database.

    public static ArrayList<Item> arrayListItem(String theloai, Context context){
        Realm.init(context);
        realmDb =Realm.getDefaultInstance();
        ArrayList<Item> listItem = new ArrayList<>(realmDb.where(Item.class).equalTo("theloai", theloai).findAll());
        return listItem;
    }

//    private RealmResults<Item> getItemArrayList(){
//        Realm.init(mContext);
//        realmDb = Realm.getDefaultInstance();
//        RealmResults<Item> listItem = realmDb.where(Item.class).findAll();
//        return listItem;
//    }

    //Check internet
    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
}
