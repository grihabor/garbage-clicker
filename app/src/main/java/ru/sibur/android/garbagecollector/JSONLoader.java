package ru.sibur.android.garbagecollector;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public  class JSONLoader {
    private final String TAG = "JSON_LOADER";
    Context context;
    public JSONLoader(Context context){
        this.context = context;
    }


    private String readRawResource (int id) {
        InputStream resourceReader = context.getResources().openRawResource(id);
        Writer writer = new StringWriter();
        try{
        BufferedReader reader = new BufferedReader(new InputStreamReader(resourceReader, "UTF-8"));
        String line = reader.readLine();
        while (line != null) {
            writer.write(line);
            line = reader.readLine();
            }
            return writer.toString();
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "Wrong format in automatas.json ");
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage()); //это непонятная ошибка, но пускай ловится и сигнализирует, если вылезает
        }finally {
        try{
            resourceReader.close();
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
            }
        }
        return null;
    }



    public JSONArray parceJSONResource(int id){
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(readRawResource(id));
            if(jsonArray == null) Log.e(TAG, "Unable to load data from automatas.json");
            } catch (JSONException e) {
            Log.e(TAG, "JSONException: "+ e.getMessage());

        }
        return jsonArray;
    }
}
