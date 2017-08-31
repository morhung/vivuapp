package vivu.com.vivuapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import vivu.com.vivuapp.Interface.ItemClickListener;
import vivu.com.vivuapp.R;
import vivu.com.vivuapp.WebViewActivity;
import vivu.com.vivuapp.model.Item;

/**
 * Created by TumPc on 8/28/2017.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHodler>{
    private LayoutInflater layoutInflater;
    private ArrayList<Item> itemArrayList;
    private Context mContext;

    private Realm realmDb;
    public ItemAdapter(Context context, ArrayList<Item> list){
        mContext = context;
        itemArrayList = list;
    }

    public void setItemArrayList(ArrayList<Item> itemArrayList) {
        this.itemArrayList = itemArrayList;
        notifyDataSetChanged();
    }

    @Override
    public ItemViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        //Tao view tu file item_details su dung LayoutInflater
        layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_details, parent, false);

        return new ItemViewHodler(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHodler holder, int position) {
        //Khoi tao database realm.
        //Gan data len item
            final Item item = itemArrayList.get(position);
            BindData(holder, item);
    }

    private void BindData(ItemViewHodler itemViewHodler, final Item item){
        itemViewHodler.mTitle.setText(item.getTitle());
        itemViewHodler.mPubDate.setText(item.getPubDate());
//        Picasso.with(layoutInflater.getContext()).load(item.getImageURL()).fit().into(holder.imageView);
        Glide.with(itemViewHodler.imageView.getContext()).load(item.getImageURL()).into(itemViewHodler.imageView);
        Log.i("theloai: ", item.getTheloai());
        Log.i("id: ", String.valueOf(item.getId()));
        itemViewHodler.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int pos, boolean isLongClick) {
                Intent intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra("LINK",item.getLink());
                mContext.startActivity(intent);
            }
        });
    }





    @Override
    public int getItemCount() {
        //tra ve tong so ban ghi dc ve~.
        return itemArrayList.size();
    }


    //xay dung viewHolder: ching la item..
    class ItemViewHodler extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        //CardView cardView;
        ImageView imageView;
        TextView mTitle, mDes, mPubDate;
        ItemClickListener itemClickListener;

        public ItemViewHodler(View itemView) {
            super(itemView);
            //cardView = (CardView) itemView.findViewById(R.id.card_view);
            imageView = (ImageView) itemView.findViewById(R.id.img_item);
            mTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mPubDate = (TextView) itemView.findViewById(R.id.tv_date);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }
        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(), false);

        }

        @Override
        public boolean onLongClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(), true);
            return true;
        }
    }
}
