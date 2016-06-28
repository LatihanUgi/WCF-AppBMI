package com.aplication.bmi.bmiaplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

/**
 * Created by Ugi Ispoyo Widodo on 5/18/2016.
 */
public class Setting extends AppCompatActivity {
    private ProgressDialog pDialog;
    SharedPreferences session;
    ListView lvrcd;
    String user;
    Button ubah;
    EditText nama_submit, password_submit, passwordlama_submit, email_submit, no_submit, ttl_submit;
    //int hasil = 0;
    String username, nama, hp, email, passwordlama, password, doj, hasil, ttl, URL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        session = getSharedPreferences("session", MODE_PRIVATE);
        user = session.getString("nama", null);

        /*nama_submit = (EditText) findViewById(R.id.et_nama);
        password_submit = (EditText) findViewById(R.id.et_password);
        passwordlama_submit = (EditText) findViewById(R.id.et_passwordlama);
        email_submit = (EditText) findViewById(R.id.et_email);
        no_submit = (EditText) findViewById(R.id.et_hp);
        ttl_submit = (EditText) findViewById(R.id.et_ttl);*/

        lvrcd = (ListView) findViewById(R.id.Listrecord);

        new ReadData().execute();

        /*ubah = (Button) findViewById(R.id.btn_ubah);

        ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
    }

    public void DataUser()
    {

        try {
            URL = "http://sahabatjam5.cloudapp.net/ProjectWCF/User.svc/GetUser/" + user;
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet request = new HttpGet(URL);

            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");

            HttpResponse response = httpClient.execute(request);

            HttpEntity responseEntity = response.getEntity();

            // Read response data into buffer
            char[] buffer = new char[(int)responseEntity.getContentLength()];
            InputStream stream = responseEntity.getContent();
            InputStreamReader reader = new InputStreamReader(stream);
            reader.read(buffer);
            stream.close();

            JSONObject record = new JSONObject(new String(buffer));

            /*JsonArray jsonArr = new JsonArray(json);
            JsonObject jsonObj = jsonArr.getJSONObject(0);*/
            JSONArray jObj = record.getJSONArray("GetUserResult");

                JSONObject jObject = (JSONObject) jObj.get(0);

            ttl_submit.setText(jObject.getString("Dob"));
            email_submit.setText(jObject.getString("Email"));
            nama_submit.setText(jObject.getString("Fullname"));
            no_submit.setText(jObject.getString("Phonenumber"));


            /*Toast.makeText(getApplicationContext(), record.toString(),
                    Toast.LENGTH_LONG).show();*/

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    class AmbilDataUser extends AsyncTask<String, String, String> {
        //ArrayList<String> list = new ArrayList<String>();
        @Override
        protected void onPreExecute() {
            try {
                super.onPreExecute();
                pDialog = new ProgressDialog(Setting.this);
                pDialog.setMessage("Mengambil Data...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(true);
                pDialog.show();
            } catch (Exception e) {
                // TODO: handle exception
                Toast.makeText(getApplicationContext(), e.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        }

        protected String doInBackground(String... args) {
            try {
                DataUser();

                /*URL = "http://sahabatjam5.cloudapp.net/ProjectWCF/User.svc/GetUser/" + user;
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
                    //JSONArray jsonArray = record.getJSONArray("GetRecordResult");
                    //for the employee json object
                    JSONArray jObj = record.getJSONArray("GetUserResult");

                    for (int i = 0; i < jObj.length(); i++) {

                        JSONObject jObject = (JSONObject) jObj.get(i);

                        list.add(jObject.getString("Fullname"));
                        list.add(jObject.getString("Email"));
                        list.add(jObject.getString("Phonenumber"));
                        list.add(jObject.getString("Dob"));
                        /*nama_submit.setText(jObject.getString("Fullname"));
                        email_submit.setText(jObject.getString("Email"));
                        no_submit.setText(jObject.getString("Phonenumber"));
                        ttl_submit.setText(jObject.getString("Dob"));
                    }

                /*URL = "http://sahabatjam5.cloudapp.net/ProjectWCF/User.svc/GetUser/" + user;
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

                    /*for (int i = 0; i < jsonArray.length(); i++) {

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
                        listItems.add(jObject.getString("Tanggal"));
                    }

                }
                }*/

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String data) {
            pDialog.dismiss();
           /* try {
                for (int i = 0; i < data.size(); i++) {
                    nama_submit.setText(i);
                }
            }
            catch (Exception e)
            {
                Toast.makeText(getApplicationContext(), e.getMessage(),
                        Toast.LENGTH_LONG).show();
            }*/
        }

    }

    public class ReadData extends AsyncTask<String, String, ArrayList> {
        ArrayList<String> listItems = new ArrayList<String>();

        @Override
        protected void onPostExecute(ArrayList data) {
            ArrayAdapter arrayAdapter =
                    new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, data );
            lvrcd.setAdapter(arrayAdapter);
            super.onPostExecute(data);

        }

        @Override
    protected ArrayList doInBackground(String... params) {
        try {
            URL = "http://192.168.43.141/ProjectWCF/User.svc/GetUser/" + user;
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
                JSONArray jsonArray = record.getJSONArray("GetUserResult");
                //for the employee json object


                /*for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jObject = (JSONObject) jsonArray.get(i);

                    // "FullName" is the property of .NET object spGetPersonsResult,
                    // and also the name of column in SQL Server 2008
                    listItems.add("Status: "+jObject.getString("Status")
                            +"\n"+"BBI: "+jObject.getString("Bbi")
                            +"\n"+"Tinggi: "+jObject.getString("Tinggi")
                            +"\n"+"Berat: "+jObject.getString("Berat")
                            +"\n"+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+jObject.getString("Tanggal"));
                        listItems.add(jObject.getString("Bbi"));
                        listItems.add(jObject.getString("Tinggi"));
                        listItems.add(jObject.getString("Berat"));
                        listItems.add(jObject.getString("Tanggal"));
                }*/
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jObject = (JSONObject) jsonArray.get(i);
                    listItems.add("Nama: "+jObject.getString("Fullname")
                            +"\n"+"Email: "+jObject.getString("Email")
                            +"\n"+"Phonenumber: "+jObject.getString("Phonenumber")
                            +"\n"+"Tanggal Lahir: "+jObject.getString("Dob")
                            +"\n"+"Password: ***");
                        /*nama_submit.setText(jObject.getString("Fullname"));
                        email_submit.setText(jObject.getString("Email"));
                        no_submit.setText(jObject.getString("Phonenumber"));
                        ttl_submit.setText(jObject.getString("Dob"));*/
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

    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(Setting.this, Home.class);
        startActivity(i);
    }

}
