package tjuri.example.com.toppop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tjuri.example.com.toppop.api.ApiClient;
import tjuri.example.com.toppop.api.ApiClientInterface;
import tjuri.example.com.toppop.model.album.AlbumDetailModel;
import tjuri.example.com.toppop.model.album.Datum_;

public class AlbumActivity extends AppCompatActivity {

    private List<Datum_> albumSongs;
    private int albumId;
    private String song;
    private String artist;
    private String album;
    private String cover;

    @BindView(R.id.img_album)
    ImageView imgAlbum;
    @BindView(R.id.tv_songName)
    TextView tvSongName;
    @BindView(R.id.tv_artistName)
    TextView tvArtistName;
    @BindView(R.id.tv_albumName)
    TextView tvAlbumName;
    @BindView(R.id.tv_albumSongs)
    TextView tvAlbumSongs;

    @BindView(R.id.view_line_divider)
    View viewDivider;

    @BindView(R.id.tv_song)
    TextView tvSong;
    @BindView(R.id.tv_artist)
    TextView tvArtist;
    @BindView(R.id.tv_album)
    TextView tvAlbum;


    @BindView(R.id.progress_bar)
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        ButterKnife.bind(this);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            albumId = bundle.getInt("albumId");
            song = bundle.getString("song");
            artist = bundle.getString("artist");
            album = bundle.getString("album");
        }

        invisibleWidgets();

        tvSongName.setText(song);
        tvArtistName.setText(artist);
        tvAlbumName.setText(album);


        ApiClientInterface apiService =
                ApiClient.getClient().create(ApiClientInterface.class);

        Call<AlbumDetailModel> call = apiService.getSongs(albumId);

        call.enqueue(new Callback<AlbumDetailModel>() {
            @Override
            public void onResponse(Call<AlbumDetailModel> call, Response<AlbumDetailModel> response) {
                if (response.isSuccessful()) {

                    albumSongs = response.body().getTracks().getData();
                    cover = response.body().getCoverBig();
                    StringBuilder sb = new StringBuilder();
                    int trackNumb = 1;
                    if (!albumSongs.isEmpty()) {
                        for (int i = 0; i < albumSongs.size(); i++) {

                            sb.append(getString(R.string.song_list, trackNumb, albumSongs.get(i).getTitle()));

                            trackNumb++;
                        }
                        tvAlbumSongs.setText(sb);
                    }
                    setDetails();
                }
            }

            @Override
            public void onFailure(Call<AlbumDetailModel> call, Throwable t) {
                call.cancel();
                visibleWidgets();
                Toast.makeText(AlbumActivity.this, "Error: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void setDetails() {
        Picasso.with(AlbumActivity.this).load(cover).into(imgAlbum, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                progressBar.setVisibility(View.GONE);
                visibleWidgets();
            }

            @Override
            public void onError() {
                progressBar.setVisibility(View.GONE);
                visibleWidgets();

            }

        });

    }

    public void visibleWidgets() {

        tvSong.setVisibility(View.VISIBLE);
        tvArtist.setVisibility(View.VISIBLE);
        tvAlbum.setVisibility(View.VISIBLE);
        tvAlbumSongs.setVisibility(View.VISIBLE);
        tvSongName.setVisibility(View.VISIBLE);
        tvArtistName.setVisibility(View.VISIBLE);
        tvAlbumName.setVisibility(View.VISIBLE);
        viewDivider.setVisibility(View.VISIBLE);
    }

    public void invisibleWidgets() {

        tvSong.setVisibility(View.INVISIBLE);
        tvArtist.setVisibility(View.INVISIBLE);
        tvAlbum.setVisibility(View.INVISIBLE);
        tvAlbumSongs.setVisibility(View.INVISIBLE);
        tvSongName.setVisibility(View.INVISIBLE);
        tvArtistName.setVisibility(View.INVISIBLE);
        tvAlbumName.setVisibility(View.INVISIBLE);
        viewDivider.setVisibility(View.INVISIBLE);

    }
}
