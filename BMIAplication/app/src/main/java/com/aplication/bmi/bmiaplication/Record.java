package com.aplication.bmi.bmiaplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.Reader;
import java.net.URLConnection;
import java.util.ArrayList;

import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ugi Ispoyo Widodo on 5/31/2016.
 */
public class Record extends AppCompatActivity {
    SharedPreferences session;
    private ProgressDialog pDialog;
    String user;
    ListView lvrcd;
    TextView tp_user;
    HttpClient client;
    String URL;
    JSONObject json;
    WebView tampil;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client2;
    //ArrayAdapter<String> arrpaket;
    //ArrayList<HashMap<String, String>> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record);
        tp_user = (TextView) findViewById(R.id.user);
        //tampil = (WebView) findViewById(R.id.webView1);
        //tampil.setBackgroundColor(Color.argb(0, 0, 0, 3));
        lvrcd = (ListView) findViewById(R.id.Listrecord);

        session = getSharedPreferences("session", MODE_PRIVATE);
        tp_user = (TextView) findViewById(R.id.user);
        user = session.getString("nama", null);
        tp_user.setText(user);

        //ArrayList<String> record = FromJSONtoArrayList();

        //ListView listView1 = (ListView)findViewById(R.id.ListView01);
        //lvrcd.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, record));
        //contactList = new ArrayList<HashMap<String, String>>();
        new ReadData().execute();
       /* ArrayList record = new ArrayList();
        try {
            URL =  "http://sahabatjam5.cloudapp.net/ProjectWCF/User.svc/GetRecord/"+user;
            HttpGet get = new HttpGet(URL);
            get.setHeader("Accept", "application/json");
            get.setHeader("Content-type", "application/json");
            HttpResponse response = client.execute(get);

            HttpEntity e = response.getEntity();
            String data = EntityUtils.toString(e);
            JSONArray recordData = new JSONArray(data);

            JSONArray DataRecord = recordData;
                /*ArrayList record = new ArrayList();

                String ddataJson = "{\"GetRecordResult\":" + record + "}";
                JSONObject ddata = new JSONObject(ddataJson);
                JSONArray personsNames = getRecordData();
            for (int i = 0; i < DataRecord.length(); i++) {
                record.add(DataRecord.getJSONObject(i).getString("Status"));
                record.add(DataRecord.getJSONObject(i).getString("Bbi"));
                record.add(DataRecord.getJSONObject(i).getString("Berat"));
                record.add(DataRecord.getJSONObject(i).getString("Tinggi"));
                record.add(DataRecord.getJSONObject(i).getString("Tanggal"));
            }


        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ArrayAdapter arrayAdapter =
                new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, record);
        lvrcd.setAdapter(arrayAdapter);*/
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

   /* public JSONArray getRecordData() throws ClientProtocolException, IOException, JSONException {
        URL = "http://sahabatjam5.cloudapp.net/ProjectWCF/User.svc/GetRecord/" + user;
        HttpGet get = new HttpGet(URL);
        get.setHeader("Accept", "application/json");
        get.setHeader("Content-type", "application/json");
        HttpResponse response = client.execute(get);
        //HttpEntity httpEntity = response.getEntity();
        int status = response.getStatusLine().getStatusCode();

        if (status == 200) //sucess
        {
            HttpEntity e = response.getEntity();
            String data = EntityUtils.toString(e);
            JSONArray recordData = new JSONArray(data);

            return recordData;
        } else {
            Toast.makeText(this, "error", Toast.LENGTH_SHORT);

            return null;
        }

    }*/

   /*class ReadData extends AsyncTask<String, String, String> {
       String html;
        @Override
        protected void onPreExecute() {
            try {
                super.onPreExecute();
                pDialog = new ProgressDialog(Record.this);
                pDialog.setMessage("Mengambil Data Record ...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(true);
                pDialog.show();
            } catch (Exception e) {
                // TODO: handle exception
                Toast.makeText(getApplicationContext(), e.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                URL =  "http://sahabatjam5.cloudapp.net/ProjectWCF/User.svc/GetRecord/"+user;
                HttpGet get = new HttpGet(URL);
                //get.setHeader("Accept", "application/json");
                //get.setHeader("Content-type", "application/json");
                HttpResponse response = client.execute(get);

                HttpEntity e = response.getEntity();
                String data = EntityUtils.toString(e);
                JSONArray recordData = new JSONArray(data);

                JSONArray DataRecord = recordData;
                ArrayList record = new ArrayList();
                /*ArrayList record = new ArrayList();

                String ddataJson = "{\"GetRecordResult\":" + record + "}";
                JSONObject ddata = new JSONObject(ddataJson);
                JSONArray personsNames = getRecordData();
                for (int i = 0; i < DataRecord.length(); i++) {
                    record.add(DataRecord.getJSONObject(i).getString("Status"));
                    /*record.add(DataRecord.getJSONObject(i).getString("Bbi"));
                    record.add(DataRecord.getJSONObject(i).getString("Berat"));
                    record.add(DataRecord.getJSONObject(i).getString("Tinggi"));
                    record.add(DataRecord.getJSONObject(i).getString("Tanggal"));*/

                    /*html = "<body'><h2 style='color:#fffccc;'>"
                            + record.add(DataRecord.getJSONObject(i).getString("Status"))
                            + "</h2>" +

                           /* + "<p style='color:#fffccc;'>Tanggal Posting: "
                            + datapengumuman.getString("tanggal")
                            + "<p style='color:#fffccc;'> Pengumuman: <br/>"
                            + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                            + datapengumuman.getString("pengumuman")
                            +"</body>";

                    Log.d("HTML", html);
                }

                //return record;

                } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String data) {
            pDialog.dismiss();
            //tampil.loadData(html, "text/html", "UTF-8");

            ArrayAdapter arrayAdapter =
                    new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, data);
            listView1.setAdapter(arrayAdapter);
        }
    }*/

   public class ReadData extends AsyncTask<String, String, ArrayList> {
       ArrayList<String> listItems = new ArrayList<String>();

       @Override
        protected void onPostExecute(ArrayList data) {
           ArrayAdapter arrayAdapter =
                   new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, data);
           lvrcd.setAdapter(arrayAdapter);
           super.onPostExecute(data);

        }

        @Override
        protected ArrayList doInBackground(String... params) {
            try {
                URL = "http://sahabatjam5.cloudapp.net/ProjectWCF/User.svc/GetRecord/" + user;
                DefaultHttpClient client = new DefaultHttpClient();
                // http get request
                HttpGet request = new HttpGet(URL);

                // set the hedear to get the data in JSON formate
                request.setHeader("Accept", "application/json");
                request.setHeader("Content-type", "application/json");

                //get the response
                HttpResponse response = client.execute(request);

                HttpEntity entity = response.getEntity();

                //if entity contect lenght 0, means no employee exist in the system with these code
                if (entity.getContentLength() != 0) {
                    // stream reader object
                    Reader DataRecord = new InputStreamReader(response.getEntity().getContent());
                    //create a buffer to fill if from reader
                    char[] buffer = new char[(int) response.getEntity().getContentLength()];
                    //fill the buffer by the help of reader
                    DataRecord.read(buffer);
                    //close the reader streams
                    DataRecord.close();

                    JSONObject record = new JSONObject(new String(buffer));
                    //JSONObject ddata = new JSONObject(ddataJson);
                    //JSONArray dataay = ddata.getJSONArray("result");
                    JSONArray jsonArray = record.getJSONArray("GetRecordResult");
                    //for the employee json object


                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jObject = (JSONObject) jsonArray.get(i);

                        // "FullName" is the property of .NET object spGetPersonsResult,
                        // and also the name of column in SQL Server 2008
                        listItems.add("Status: "+jObject.getString("Status")
                                +"\n"+"BBI: "+jObject.getString("Bbi")
                                +"\n"+"Tinggi: "+jObject.getString("Tinggi")
                                +"\n"+"Berat: "+jObject.getString("Berat")
                                +"\n"+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+jObject.getString("Tanggal"));
                        /*listItems.add(jObject.getString("Bbi"));
                        listItems.add(jObject.getString("Tinggi"));
                        listItems.add(jObject.getString("Berat"));
                        listItems.add(jObject.getString("Tanggal"));*/
                    }

                    //set the text of text view
               /* tvEmployeeCode.setText("Code: " + employee.getString("EmployeeId"));
                tvName.setText("Name: " + employee.getString("FirstName") + " " + employee.getString("LastName"));
                tvAddress.setText("Address: " + employee.getString("Address"));
                tvBloodGroup.setText("Blood Group: " + employee.getString("BloodGroup"));

                //show hide layout
                linearLayoutError.setVisibility(View.GONE);
                linearLayoutEmp.setVisibility(View.VISIBLE);*/
                }

            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return listItems;
        }

    }

    /*public ArrayList<String> FromJSONtoArrayList() {
        ArrayList<String> listItems = new ArrayList<String>();

        try {

            // Replace it with your own WCF service path
            //URL json = new GoWcf("http://192.168.1.1:9020/Service.svc/GetPersonsJSON");
            //URLConnection jc = json.openConnection();

            /*URL = "http://sahabatjam5.cloudapp.net/ProjectWCF/User.svc/GetRecord/" + user;
            HttpGet get = new HttpGet(URL);
            get.setHeader("Accept", "application/json");
            get.setHeader("Content-type", "application/json");
            //JSONObject jsonObject = GoWcf.goWCFService(URL);
            //BufferedReader reader = new BufferedReader(new InputStreamReader(URL));

            //String line = reader.readLine();

            URL = "http://sahabatjam5.cloudapp.net/ProjectWCF/User.svc/GetRecord/" + user;
            HttpGet get = new HttpGet(URL);
            get.setHeader("Accept", "application/json");
            get.setHeader("Content-type", "application/json");
            HttpResponse response = client.execute(get);

            HttpEntity e = response.getEntity();
            String data = EntityUtils.toString(e);
            JSONArray jsonArray = new JSONArray(data);
            BufferedReader reader = new BufferedReader(new InputStreamReader(e.getContent()));

            String line = reader.readLine();

            // recordData = new JSONArray(data);

            //JSONObject jsonResponse = new JSONObject(get.getMethod());
            //JSONArray jsonArray = new getJSONArray(data);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jObject = (JSONObject) jsonArray.get(i);

                // "FullName" is the property of .NET object spGetPersonsResult,
                // and also the name of column in SQL Server 2008
                listItems.add(jObject.getString("Status"));
            }

            //reader.close();

        } catch (Exception e) {
            e.printStackTrace();
            //Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }

        return listItems;
    }*/

    /*public ArrayList<String> FromJSONtoArrayList() {
        ArrayList<String> listItems = new ArrayList<String>();
        try {
            URL = "http://sahabatjam5.cloudapp.net/ProjectWCF/User.svc/GetRecord/" + user;
            DefaultHttpClient client = new DefaultHttpClient();
            // http get request
            HttpGet request = new HttpGet(URL);

            // set the hedear to get the data in JSON formate
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");

            //get the response
            HttpResponse response = client.execute(request);

            HttpEntity entity = response.getEntity();

            //if entity contect lenght 0, means no employee exist in the system with these code
            if (entity.getContentLength() != 0) {
                // stream reader object
                Reader DataRecord = new InputStreamReader(response.getEntity().getContent());
                //create a buffer to fill if from reader
                char[] buffer = new char[(int) response.getEntity().getContentLength()];
                //fill the buffer by the help of reader
                DataRecord.read(buffer);
                //close the reader streams
                DataRecord.close();

                JSONObject record = new JSONObject(new String(buffer));
                //JSONObject ddata = new JSONObject(ddataJson);
                //JSONArray dataay = ddata.getJSONArray("result");
                JSONArray jsonArray = record.getJSONArray("");
                //for the employee json object


                for (int i = 0; i < jsonArray.length(); i++) {

                    //JSONObject jObject = (JSONObject) jsonArray.get(i);

                    // "FullName" is the property of .NET object spGetPersonsResult,
                    // and also the name of column in SQL Server 2008
                    listItems.add(record.getString("Status"));
                }

                //set the text of text view
               /* tvEmployeeCode.setText("Code: " + employee.getString("EmployeeId"));
                tvName.setText("Name: " + employee.getString("FirstName") + " " + employee.getString("LastName"));
                tvAddress.setText("Address: " + employee.getString("Address"));
                tvBloodGroup.setText("Blood Group: " + employee.getString("BloodGroup"));

                //show hide layout
                linearLayoutError.setVisibility(View.GONE);
                linearLayoutEmp.setVisibility(View.VISIBLE);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
        return listItems;

    }*/

    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(Record.this, Home.class);
        startActivity(i);
    }

}