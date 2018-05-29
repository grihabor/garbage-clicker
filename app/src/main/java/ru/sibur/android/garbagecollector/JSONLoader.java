package ru.sibur.android.garbagecollector;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;

public  class JSONLoader {
    Context context;
    public JSONLoader(Context context){
        this.context = context;
    }

    public String readJson(int id){
        InputStream resourceReader = context.getResources().openRawResource(id);
        Writer writer = new StringWriter();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(resourceReader, "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                writer.write(line);
                line = reader.readLine();
            }
            return writer.toString();
        } catch (Exception e) {
            Log.e("MYAPP", "exception: " + e.getMessage());
            Log.e("MYAPP", "exception: " + e.toString());
        } finally {
            try {
                resourceReader.close();
            } catch (Exception e) {
                Log.e("MYAPP", "exception: " + e.getMessage());
                Log.e("MYAPP", "exception: " + e.toString());
            }
        }
        return null;
    }

    public JSONArray loadShopItemData(int id){

        try {
            JSONArray jsonarray = new JSONArray(readJson(id));
            return jsonarray;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
