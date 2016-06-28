package com.aplication.bmi.bmiaplication;

/**
 * Created by Ugi Ispoyo Widodo on 6/3/2016.
 */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Tips.JSONParser;

@SuppressLint("NewApi")
public class TipsDetail extends Activity {

    String tips;
    JSONObject datatips;
    // TextView tampil;

    String id_tips;

    WebView tampil;

    // Progress Dialog
    private ProgressDialog pDialog;

    // JSON parser class
    JSONParser jsonParser = new JSONParser();

    // single event url
    private static final String url_details = "http://192.168.43.141/ProjectWCF/User.svc/GetArtikel/Detail/";

    private static final String IDtips = "id_Tips";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tips_detail);

        tampil = (WebView) findViewById(R.id.webView1);
        tampil.setBackgroundColor(Color.argb(0, 0, 0, 3));

        // getting event details from intent
        Intent i = getIntent();

        // getting event id (idievent) from intent
        id_tips = i.getStringExtra(IDtips);

        // Getting complete event details in background thread
        new GetEventDetail().execute();

    }

    /**
     * Background Async Task to Get complete event details
     */
    class GetEventDetail extends AsyncTask<String, String, String> {
        String html;

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(TipsDetail.this);
            pDialog.setMessage("Mengambil data...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Getting event details in background thread
         */
        protected String doInBackground(String... params) {

            try {
                // Building Parameters
                /*List<NameValuePair> params1 = new ArrayList<NameValuePair>();
                params1.add(new BasicNameValuePair("Id_Artikel",
                        id_tips));*/

                // getting event details by making HTTP request
                // Note that event details url will use GET request
                JSONObject json = jsonParser.makeHttpRequest(url_details + id_tips /*"GET", params1*/);

                // check your log for json response
                Log.d("JSON", json.toString());

                if (json.length() > 0) {
                    // successfully received event details
                    JSONArray eventObject = json
                            .getJSONArray("GetArtikelResult"); // JSON

                    // get first event object from JSON Array
                    datatips = eventObject.getJSONObject(0);

                    Log.d("tips", datatips.toString());

                    try {
                        // build html parser
                        // * cek status
                        // judul=event.getString("judul");
                        html = "<body'><h2 style='color:#fffccc;'>"
                                + datatips.getString("Judul")
                                + "</h2>"
                                + "<img src='http://192.168.43.141/ProjectWCFWeb/photoartikel/"
                                + datatips.getString("Gambar") + "' width='300px' height='150px'>"
                                + "<p style='color:#fffccc;'>Tanggal Posting: "
                                + datatips.getString("Tanggal")
                                + "<p style='color:#fffccc;'><br/>"
                                + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                                + datatips.getString("Artikel")
                                + "</body>";

                        Log.d("HTML", html);

                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } // Array

                } else {
                    // event with idievent not found
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         **/
        protected void onPostExecute(String filename) {
            // dismiss the dialog once got all details
            pDialog.dismiss();
            tampil.loadData(html, "text/html", "UTF-8");
            // tampil.setText(html));

        }
    }

    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent i = new Intent(TipsDetail.this, TipsActivity.class);
        startActivity(i);
        finish();
    }

}
