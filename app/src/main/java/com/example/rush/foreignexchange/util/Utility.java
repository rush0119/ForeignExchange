package com.example.rush.foreignexchange.util;

import android.text.TextUtils;

import com.example.rush.foreignexchange.gson.ForeignExchange;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rush on 2018/5/8.
 */

public class Utility {
    public static List<ForeignExchange> handleForeignExchangeResponse(String response){
        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray foreignExchanges=new JSONArray(response);
                Type type=new TypeToken<ArrayList<ForeignExchange>>(){}.getType();
                return new Gson().fromJson(response,type);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
