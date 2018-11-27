package tjuri.example.com.toppop.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import tjuri.example.com.toppop.model.album.AlbumDetailModel;
import tjuri.example.com.toppop.model.chart.TrackDetailsModel;

public interface ApiClientInterface {

    @GET("chart")
    Call<TrackDetailsModel> getDatum();

    @GET("album/{album_id}")
    Call<AlbumDetailModel> getSongs(@Path("album_id") int album);
}

