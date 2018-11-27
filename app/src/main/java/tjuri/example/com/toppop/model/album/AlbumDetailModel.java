package tjuri.example.com.toppop.model.album;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlbumDetailModel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("cover")
    @Expose
    private String cover;
    @SerializedName("cover_small")
    @Expose
    private String coverSmall;
    @SerializedName("cover_medium")
    @Expose
    private String coverMedium;
    @SerializedName("cover_big")
    @Expose
    private String coverBig;
    @SerializedName("tracks")
    @Expose
    private Tracks tracks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public String getCoverBig() {
        return coverBig;
    }

    public Tracks getTracks() {
        return tracks;
    }


}