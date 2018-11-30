package tjuri.example.com.toppop.model.chart;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tracks {

    @SerializedName("data")
    @Expose
    private List<Track> data = null;

    public List<Track> getData() {
        return data;
    }



}