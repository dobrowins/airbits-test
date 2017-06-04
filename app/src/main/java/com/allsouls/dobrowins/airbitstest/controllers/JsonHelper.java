package com.allsouls.dobrowins.airbitstest.controllers;

import android.content.Context;

import com.allsouls.dobrowins.airbitstest.R;
import com.allsouls.dobrowins.airbitstest.models.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Class to move all the json-gibberish needed to initialize recyclerView out of MainActivity's
 * onCreate method
 *
 * Created on 6/3/2017.
 */

public class JsonHelper {

    private Context context;

    public JsonHelper(Context context) {
        this.context = context;
    }

    public ArrayList<Product> provideArrayListFromJson() {
        Gson gson = new Gson();
        Type founderListType = new TypeToken<ArrayList<Product>>(){}.getType();
        return gson.fromJson(provideJSONasString(), founderListType);
    }

    private String provideJSONasString() {
        String finalJsonString = null, line;
        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream = context.getResources().openRawResource(R.raw.products);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            finalJsonString = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                stringBuilder = null;
                line = null;
                bufferedReader.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return finalJsonString;
    }
}
