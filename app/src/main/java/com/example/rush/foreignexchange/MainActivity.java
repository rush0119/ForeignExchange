package com.example.rush.foreignexchange;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.rush.foreignexchange.adapter.RateAdapter;
import com.example.rush.foreignexchange.gson.ForeignExchange;
import com.example.rush.foreignexchange.util.HttpUtil;
import com.example.rush.foreignexchange.util.Utility;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private List<ForeignExchange> foreignExchangeList=new ArrayList<>();
    private RecyclerView rateRecyclerView;
    public SwipeRefreshLayout swipeRefresh;
    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout) ;
        swipeRefresh=(SwipeRefreshLayout) findViewById(R.id.swiper_refresh) ;
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestRateList();
            }
        });
//        swipeRefresh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawerLayout.openDrawer(GravityCompat.START);
//            }
//        });
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(this);
        String rateListString=prefs.getString("rateList",null);
        if (rateListString!=null){
            foreignExchangeList= Utility.handleForeignExchangeResponse(rateListString);
            showRateList(foreignExchangeList);
        }else {
            requestRateList();
        }

    }
    public void showRateList(List<ForeignExchange> foreignExchangeList){
        rateRecyclerView=(RecyclerView) findViewById(R.id.rate_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        rateRecyclerView.setLayoutManager(layoutManager);
        RateAdapter adapter=new RateAdapter(foreignExchangeList);
        rateRecyclerView.setAdapter(adapter);

    }
    public void requestRateList(){
        String rateUrl="https://test.bankoffs.com.cn/international/rateformobile";
        HttpUtil.sendOkHttpRequest(rateUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(MainActivity.this, "获取信息失败", Toast.LENGTH_SHORT).show();
                swipeRefresh.setRefreshing(false);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText=response.body().string();
                final List<ForeignExchange> foreignExchangeList=Utility.handleForeignExchangeResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (foreignExchangeList!=null){
                            SharedPreferences.Editor editor=PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit();
                            editor.putString("rateList",responseText);
                            editor.apply();
                            showRateList(foreignExchangeList);
                        }
                        else {
                            Toast.makeText(MainActivity.this, "获取信息失败1", Toast.LENGTH_SHORT).show();
                        }
                        swipeRefresh.setRefreshing(false);
                    }
                });

            }
        });
    }
}
