package tjuri.example.com.toppop.model.album;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tracks {

    @SerializedName("data")
    @Expose
    private List<Album> data = null;

    public List<Album> getData() {
        return data;
    }


}