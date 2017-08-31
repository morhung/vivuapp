package vivu.com.vivuapp.model;


import android.widget.ImageView;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

public class Item extends RealmObject{
    @PrimaryKey
    @Ignore
    private int id;
    private String title;

    private String link;
    private String imageURL;
    private String pubDate;

    private String theloai;

    public Item() {
    }

    public Item(String title, String link, String imageURL, String pubDate, String theloai) {
        this.title = title;
        this.link = link;
        this.imageURL = imageURL;
        this.pubDate = pubDate;
        this.theloai = theloai;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
