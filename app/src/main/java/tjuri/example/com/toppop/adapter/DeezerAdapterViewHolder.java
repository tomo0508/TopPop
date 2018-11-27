package tjuri.example.com.toppop.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import tjuri.example.com.toppop.R;

public class DeezerAdapterViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.list_item_position)
    TextView tvPosition;
    @BindView(R.id.list_item_song)
    TextView tvSong;
    @BindView(R.id.list_item_artist)
    TextView tvArtist;
    @BindView(R.id.list_item_duration)
    TextView tvDuration;


    public DeezerAdapterViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}