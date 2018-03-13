package com.example.maddiemaniaci.stockquotes_maniaci;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by maddiemaniaci on 3/13/18.
 */

public class JsonUtils {
    public static JSONObject parseStockQuoteJson(String json) {
        JSONObject quote = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            quote = jsonObject.getJSONObject("quote");
            Log.i("JSON", quote.toString());
        } catch (Exception e){
            Log.e("JSON", "Error parsing JSON");
        }
        return quote;
    }
}
