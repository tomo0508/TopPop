package tjuri.example.com.toppop;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tjuri.example.com.toppop.adapter.DeezerAdapter;
import tjuri.example.com.toppop.api.ApiClient;
import tjuri.example.com.toppop.api.ApiClientInterface;
import tjuri.example.com.toppop.model.chart.Datum;
import tjuri.example.com.toppop.model.chart.TrackDetailsModel;
import tjuri.example.com.toppop.ui.NoInternetDialog;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {


    private List<Datum> tracks;
    //private List<Datum> orderNormal;
    @BindView(R.id.rv_chart)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLayout();

        if (!isOnline()) {
            NoInternetDialog noInternet = new NoInternetDialog();
            noInternet.show(getSupportFragmentManager(), "No Internet");

        }

        swipeContainer.setOnRefreshListener(this);
        swipeContainer.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        swipeContainer.post(new Runnable() {
            @Override
            public void run() {

                loadRecyclerViewData();
            }
        });

    }


    @Override
    public void onRefresh() {
        loadRecyclerViewData();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (!isOnline()) {
            Toast.makeText(this, "No Internet. List can't be refreshed.", Toast.LENGTH_LONG).show();

        } else
            onRefresh();

    }


    private void loadRecyclerViewData() {

        swipeContainer.setRefreshing(true);

        ApiClientInterface apiService =
                ApiClient.getClient().create(ApiClientInterface.class);

        Call<TrackDetailsModel> call = apiService.getDatum();

        call.enqueue(new Callback<TrackDetailsModel>() {
            @Override
            public void onResponse(Call<TrackDetailsModel> call, Response<TrackDetailsModel> response) {
                if (response.isSuccessful()) {
                    tracks = response.body().getTracks().getData();
                    //orderNormal = new ArrayList<>(tracks);
                    adapterSet(tracks);
                    swipeContainer.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<TrackDetailsModel> call, Throwable t) {
                call.cancel();
                swipeContainer.setRefreshing(false);
                Toast.makeText(MainActivity.this, "Error: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initLayout() {
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item_normal) {
            Collections.sort(tracks, Datum::orderNormal);
            adapterSet(tracks);
            return true;
        } else if (id == R.id.item_asc) {
            Collections.sort(tracks, Datum::orderAsc);
            adapterSet(tracks);
            return true;
        } else if (id == R.id.item_desc) {
            Collections.sort(tracks, Datum::orderDesc);
            adapterSet(tracks);
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    private void adapterSet(List<Datum> list) {
        DeezerAdapter dAdapter;
        dAdapter = new DeezerAdapter(list, this);
        recyclerView.setAdapter(dAdapter);
    }

    private boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager)
                getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected() && netInfo.isAvailable();
    }


}
