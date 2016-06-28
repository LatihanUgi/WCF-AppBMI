package com.aplication.bmi.bmiaplication;

/**
 * Created by Ugi Ispoyo Widodo on 6/3/2016.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Tips.AppConfig;
import Tips.JSONParser;

public class TipsActivity extends Activity {

    private ListView lvEvent;

    JSONParser jParser;
    // Progress Dialog
    private ProgressDialog pDialog;
    List<Tips> listevent;
    JSONArray arrayevent;
    private static final String VIEW_EVENT_URL = AppConfig.SERVER;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tips_list);

        lvEvent = (ListView) findViewById(R.id.listevent);
        listevent = new ArrayList<Tips>();
        listevent.clear();
        jParser = new JSONParser();

    }

    private void updateList() {

        TipsAdapter adapter = new TipsAdapter(this, listevent);
        lvEvent.setAdapter(adapter);
        // to do nothing...

        lvEvent.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // getting values from selected ListItem
                String id_Tips = ((TextView) view
                        .findViewById(R.id.tvIDArtikel)).getText()
                        .toString();

                // Starting new intent
                Intent in = new Intent(getApplicationContext(),
                        TipsDetail.class);
                // sending idnama_kasus to next activity
                in.putExtra("id_Tips", id_Tips);

                // starting new activity and expecting some response back
                startActivityForResult(in, 100);
                finish();

            }
        });

    }

    public void updateJSONdata() {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        JSONObject json = jParser
                .makeHttpRequest(VIEW_EVENT_URL/*, "GET", params*/);
        Log.d("JSON", json.toString());
        // when parsing JSON stuff, we should probably
        // try to catch any exceptions:
        try {

            // I know I said we would check if "Posts were Avail." (success==1)
            // before we tried to read the individual posts, but I lied...
            // mComments will tell us how many "posts" or comments are
            // available
            arrayevent = json.getJSONArray("GetAllArtikelResult");

            // looping through all posts according to the json object returned
            for (int i = 0; i < arrayevent.length(); i++) {
                JSONObject event = arrayevent.getJSONObject(i);
                listevent.add(new Tips(event.getString("Id_Artikel"),
                        event.getString("Judul"), event.getString("Tanggal"),
                        event.getString("Artikel"))); // end of add

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        // loading the comments via AsyncTask
        listevent.clear();
        new Loadevent().execute();
    }

    public class Loadevent extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(TipsActivity.this);
            pDialog.setMessage("Loading data...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
            pDialog.dismiss();
        }

        @Override
        protected Boolean doInBackground(Void... arg0) {
            updateJSONdata();
            return null;

        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            pDialog.dismiss();
            updateList();
        }

    }


    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent i = new Intent(TipsActivity.this, Home.class);
        startActivity(i);
        finish();
    }
}