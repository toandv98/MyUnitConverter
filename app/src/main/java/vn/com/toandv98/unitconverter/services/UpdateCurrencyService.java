package vn.com.toandv98.unitconverter.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.Map;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.com.toandv98.unitconverter.R;
import vn.com.toandv98.unitconverter.data.entities.LastRates;
import vn.com.toandv98.unitconverter.data.local.PreferencesHelper;
import vn.com.toandv98.unitconverter.data.remote.RetrofitClient;

import static vn.com.toandv98.unitconverter.utils.Constrants.ACTION_UPDATE_RATES;
import static vn.com.toandv98.unitconverter.utils.Constrants.EXTRA_NAME_RESULT_MSG;
import static vn.com.toandv98.unitconverter.utils.Constrants.FIXED_IO_API_KEY;
import static vn.com.toandv98.unitconverter.utils.Constrants.MAP_QUERY_KEY;

public class UpdateCurrencyService extends JobIntentService {

    public static final int NOFTIFICATION_UPDATE_RATES_ID = 1003;
    public static final int CURRENCY_SERVICE_ID = 1001;
    public static final String CHANNEL_UPDATE_ID = "1002";

    public static void enqueueWork(Context context, Intent intent) {
        enqueueWork(context, UpdateCurrencyService.class, CURRENCY_SERVICE_ID, intent);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_UPDATE_ID,
                    getString(R.string.channel_name_update), NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(getString(R.string.description_update_channel));
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_UPDATE_ID)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(getString(R.string.title_updating))
                .setContentText(getString(R.string.text_updating_rates))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setUsesChronometer(true)
                .setProgress(0, 0, true);

        final NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        startForeground(NOFTIFICATION_UPDATE_RATES_ID, builder.build());

        Map<String, String> mapQuery = new TreeMap<>();
        mapQuery.put(MAP_QUERY_KEY, FIXED_IO_API_KEY);

        RetrofitClient.getApiService().getLastRates(mapQuery).enqueue(new Callback<LastRates>() {
            @SuppressWarnings("NullableProblems")
            @Override
            public void onResponse(Call<LastRates> call, Response<LastRates> response) {
                if (response.isSuccessful() && response.body() != null) {
                    PreferencesHelper.insertToSP(getApplicationContext(), response.body().getRates());
                    builder.setContentText(getString(R.string.msg_update_data_successfully))
                            .setProgress(0, 0, false)
                            .setUsesChronometer(false);
                    managerCompat.notify(NOFTIFICATION_UPDATE_RATES_ID, builder.build());

                    Intent broadcastIntent = new Intent(ACTION_UPDATE_RATES);
                    broadcastIntent.putExtra(EXTRA_NAME_RESULT_MSG, getString(R.string.msg_update_data_successfully));
                    LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(broadcastIntent);
                }
            }

            @SuppressWarnings("NullableProblems")
            @Override
            public void onFailure(Call<LastRates> call, Throwable t) {
                builder.setContentText(getString(R.string.msg_error_update_rates) + t.getMessage())
                        .setProgress(0, 0, false)
                        .setUsesChronometer(false);
                managerCompat.notify(NOFTIFICATION_UPDATE_RATES_ID, builder.build());

                Intent broadcastIntent = new Intent(ACTION_UPDATE_RATES);
                broadcastIntent.putExtra(EXTRA_NAME_RESULT_MSG,
                        getString(R.string.msg_error_update_rates) + t.getMessage());
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(broadcastIntent);
            }
        });
    }
}
