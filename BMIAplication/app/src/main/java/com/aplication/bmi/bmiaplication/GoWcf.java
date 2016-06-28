package com.aplication.bmi.bmiaplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.wifi.WifiManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Ugi Ispoyo Widodo on 5/24/2016.
 */
public class GoWcf {
    static JSONObject JsonObject;

    public static JSONObject goWCFService(String inputjson) {
        String url;
        android.util.Log.i("WCFIP", inputjson);
        try {
            //url = URLEncoder.encode(inputjson, "UTF-8");

            HttpGet request = new HttpGet(inputjson);
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpResponse response = httpClient.execute(request);

            HttpEntity responseEntity = response.getEntity();
            char[] buffer = new char[(int) responseEntity.getContentLength()];
            InputStream stream = responseEntity.getContent();
            InputStreamReader reader = new InputStreamReader(stream);
            reader.read(buffer);
            /*StringBuilder sb = new StringBuilder();


            BufferedReader reader = new BufferedReader(new InputStreamReader(stream,"iso-8859-1"),8);
            //StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }*/
            stream.close();
            JsonObject = new JSONObject(new String(buffer));
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JsonObject;
    }

    public static Drawable LoadImageFromWeb(String string) {
        try {
            InputStream is = (InputStream) new URL(string).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            System.out.println("Exc=" + e);
            return null;
        }
    }
}
