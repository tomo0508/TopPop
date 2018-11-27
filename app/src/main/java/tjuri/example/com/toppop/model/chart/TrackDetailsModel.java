package tjuri.example.com.toppop.model.chart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrackDetailsModel {

    @SerializedName("tracks")
    @Expose
    public Tracks tracks;

    public Tracks getTracks() {
        return tracks;
    }



}
