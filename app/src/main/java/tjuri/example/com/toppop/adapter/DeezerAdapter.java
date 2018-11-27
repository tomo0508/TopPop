package tjuri.example.com.toppop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Locale;

import tjuri.example.com.toppop.AlbumActivity;
import tjuri.example.com.toppop.R;
import tjuri.example.com.toppop.model.chart.Datum;


public class DeezerAdapter extends RecyclerView.Adapter<DeezerAdapterViewHolder> {


    private List<Datum> tracks;
    private int rowLayout;
    private Context context;


    public DeezerAdapter(List<Datum> tracks, int rowLayout, Context context) {
        this.tracks = tracks;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @NonNull
    @Override
    public DeezerAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_tracks, parent, false);
        return new DeezerAdapterViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull DeezerAdapterViewHolder holder, int position) {
        holder.tvPosition.setText(tracks.get(position).position.toString());
        holder.tvSong.setText(tracks.get(position).title);
        holder.tvArtist.setText(tracks.get(position).artist.name);
        holder.tvDuration.setText(formatDuration(tracks.get(position).duration));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int album = tracks.get(position).album.id;

                String songName = holder.tvSong.getText().toString();
                String artistName = holder.tvArtist.getText().toString();
                String albumName = tracks.get(position).album.title;
                Intent intent = new Intent(view.getContext(), AlbumActivity.class);

                intent.putExtra("albumId", album);
                intent.putExtra("song", songName);
                intent.putExtra("artist", artistName);
                intent.putExtra("album", albumName);

                view.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        if (tracks != null) {
            return tracks.size();
        }

        return -1;
    }


    public String formatDuration(int duration) {

        int min = (duration / 60) % 60;
        int sec = duration % 60;
        return String.format("%02d:%02d", min, sec);

    }
}
