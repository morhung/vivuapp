package vivu.com.vivuapp.model;


import android.widget.ImageView;

public class Item {
    private String title;
    private String link;
    private String imageURL;
    private String pubDate;


    public Item() {
    }

    public Item(String title, String link, String imageURL, String pubDate) {
        this.title = title;
        this.link = link;
        this.imageURL = imageURL;
        this.pubDate = pubDate;
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
}
