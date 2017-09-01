package vivu.com.vivuapp.asyn;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import io.realm.Realm;
import vivu.com.vivuapp.adapter.ItemAdapter;
import vivu.com.vivuapp.model.Item;

public class LoadData extends AsyncTask<String, Void, ArrayList<Item>>{
    ProgressDialog progressDialog;
    ArrayList<Item> result = new ArrayList<>();

    ItemAdapter itemAdapter;
    Context context;
    String theloai;
    private Realm realmBb;
    public LoadData(ItemAdapter itemAdapter, Context context, String theloai) {
        this.itemAdapter = itemAdapter;
        this.context = context;
        this.theloai = theloai;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);

        progressDialog.setMessage("Loading.....");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
    private void getData(String url){
        Realm.init(context);

        try {

            Document doc = Jsoup.connect(url).get();
            Elements element = doc.select("item");
            for (Element item : element){
                String title = item.select("title").text();
                String link = item.select("link").text();
                String pubDate = item.select("pubDate").text();
                String des = item.select("description").text();

                //HTML => dung Jsoup parse no tiep => img

                Document docImg = Jsoup.parse(des);
                String imgURL = docImg.select("img").get(0).attr("src");
                final Item mItem = new Item(title, link, imgURL, pubDate, theloai);
                result.add(mItem);

                realmBb = Realm.getDefaultInstance();
                realmBb.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Item save = realm.createObject(Item.class);
                        save.setTitle(mItem.getTitle());
                        save.setLink(mItem.getLink());
                        save.setImageURL(mItem.getImageURL());
                        save.setPubDate(mItem.getPubDate());
                        save.setTheloai(theloai);
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected ArrayList<Item> doInBackground(String... strings) {

        for (int i = 0; i < strings.length; i++){
            String url = strings[i];
            getData(url);
        }
        return result;
    }

    @Override
    protected void onPostExecute(ArrayList<Item> itemArrayList) {
        super.onPostExecute(itemArrayList);
        progressDialog.dismiss();
        itemAdapter.setItemArrayList(itemArrayList);
        Log.i("NewFeedFragment", "onPostExecute: item loaded size = " +itemArrayList.size());
    }
}
