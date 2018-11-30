package tjuri.example.com.toppop.model.chart;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Track{

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("duration")
    @Expose
    public Integer duration;
    @SerializedName("position")
    @Expose
    public Integer position;
    @SerializedName("artist")
    @Expose
    public Artist artist;
    @SerializedName("album")
    @Expose
    public Album album;

    public String getTitle() {
        return title;
    }

    public int orderNormal(Track o) {
        int i = this.position.compareTo(o.position);
        return i;
    }

    public int orderAsc(Track o) {
        int i = this.duration.compareTo(o.duration);
        return i;
    }

    public int orderDesc(Track o) {
        int i = this.duration.compareTo(o.duration);
        return -i;
    }

}