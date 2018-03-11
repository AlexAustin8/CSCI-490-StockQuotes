package com.example.alex.stockquotes;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by alex on 3/9/18.
 */

public class JsonUtils {
    public static JSONObject parseStockQuoteJson(String json) {
        JSONObject quote = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            quote = jsonObject.getJSONObject("quote");
            Log.i("JSON", quote.toString());

        } catch (Exception ex) {
            Log.e("JSON", "Error parsing JSON");
        }
        return quote;
    }
}

