package ru.sibur.android.garbagecollector;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
            Log.e(TAG, "Wrong format in json file: id=" + id);
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



    public JSONObject parceJSONResource(int id){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(readRawResource(id));
            if(jsonObject == null) Log.e(TAG, "Unable to load data from json file: id="+id);
            } catch (JSONException e) {
            Log.e(TAG, "JSONException: "+ e.getMessage());

        }
        return jsonObject;
    }
}
