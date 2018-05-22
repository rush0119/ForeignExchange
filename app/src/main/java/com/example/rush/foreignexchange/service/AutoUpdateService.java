package com.example.rush.foreignexchange.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v4.content.SharedPreferencesCompat;
import android.widget.Toast;

import com.example.rush.foreignexchange.gson.ForeignExchange;
import com.example.rush.foreignexchange.util.HttpUtil;
import com.example.rush.foreignexchange.util.Utility;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AutoUpdateService extends Service {
    public AutoUpdateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        AlarmManager manager=(AlarmManager) getSystemService(ALARM_SERVICE);
        int anMinute=15*60*1000;
        long traggerAtTime= SystemClock.elapsedRealtime()+anMinute;
        Intent i=new Intent(this,AutoUpdateService.class);
        PendingIntent pi=PendingIntent.getService(this,0,i,0);
        manager.cancel(pi);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,traggerAtTime,pi);

        return super.onStartCommand(intent, flags, startId);
    }
    private void updateForeignExchange(){
        String rateUrl="https://test.bankoffs.com.cn/international/rateformobile";
        HttpUtil.sendOkHttpRequest(rateUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText=response.body().string();
                List<ForeignExchange> foreignExchangeListUpdate=Utility.handleForeignExchangeResponse(responseText);
                if (foreignExchangeListUpdate!=null){
                    SharedPreferences.Editor editor=PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
                    editor.putString("rateList",null);
                    editor.apply();
                }

            }
        });

    }

}
