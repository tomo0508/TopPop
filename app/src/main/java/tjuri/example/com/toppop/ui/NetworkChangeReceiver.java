package tjuri.example.com.toppop.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {

        if (isOnline(context))

            Toast.makeText(context, "Internet OK", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(context, "No Internet", Toast.LENGTH_LONG).show();


    }




    boolean isOnline(Context context) {
        ServiceManager serviceManager = new ServiceManager(context);
        if (serviceManager.isNetworkAvailable()) {
            return true;
        } else {
            return false;
        }
    }

}