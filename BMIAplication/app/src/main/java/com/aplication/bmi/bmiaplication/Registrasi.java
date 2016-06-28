package com.aplication.bmi.bmiaplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import android.app.Activity;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Ugi Ispoyo Widodo on 5/18/2016.
 */
public class Registrasi extends AppCompatActivity {
    private ProgressDialog pDialog;
    Button register;
    EditText nama_submit, password_submit, username_submit, email_submit, no_submit, tgl_submit, bln_submit, thn_submit;
    RadioButton rb_pria, rb_wanita;
    //int hasil = 0;
    String username, jk, nama, tgl, bln, thn, hp, email, password, doj, hasil, ttl, URL;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrasi);
        nama_submit = (EditText) findViewById(R.id.et_nama);
        password_submit = (EditText) findViewById(R.id.et_password);
        username_submit = (EditText) findViewById(R.id.et_username);
        rb_pria = (RadioButton) findViewById(R.id.rb_p);
        rb_wanita = (RadioButton) findViewById(R.id.rb_w);
        email_submit = (EditText) findViewById(R.id.et_email);
        no_submit = (EditText) findViewById(R.id.et_hp);
        tgl_submit = (EditText) findViewById(R.id.et_ttl);
        bln_submit = (EditText) findViewById(R.id.et_ttl1);
        thn_submit = (EditText) findViewById(R.id.et_ttl2);
        register = (Button) findViewById(R.id.btn_register);

        // Get Date
        DateFormat df = new SimpleDateFormat("d-MMM-yyyy");
        String date = df.format(Calendar.getInstance().getTime());
        doj = date;

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Registrasi.this);
                alertDialog.setTitle("Peringatan!");
                alertDialog.setMessage("Apakah Data Anda Sudah Benar ?");
                alertDialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ConnectionDetector cd;
                        cd = new ConnectionDetector(getApplicationContext());
                        if (!cd.isConnectingToInternet()) {
                            Toast.makeText(getApplicationContext(), "Tidak Ada Koneksi Ke Server", Toast.LENGTH_LONG).show();
                            setContentView(R.layout.registrasi);
                        } else {
                            username = username_submit.getText().toString();
                            jk = (rb_pria.isChecked() ? "P" : "W").toString();
                            nama = nama_submit.getText().toString();
                            tgl = tgl_submit.getText().toString();
                            bln = bln_submit.getText().toString();
                            thn = thn_submit.getText().toString();
                            hp = no_submit.getText().toString();
                            email = email_submit.getText().toString();
                            password = password_submit.getText().toString();

                            ttl = tgl + "-" + bln + "-" + thn;
                            new TambahUser().execute();

                            int SDK_INT = android.os.Build.VERSION.SDK_INT;
                            if (SDK_INT > 8) {
                                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                                        .permitAll().build();
                                StrictMode.setThreadPolicy(policy);
                                /*URL = "http://192.168.43.141/ProjectWCF/User.svc/AddUser/"
                                        + username + "/" + jk + "/"
                                        + nama + "/" + ttl + "/" + hp + "/" + email + "/"
                                        + password + doj;*/
                                //JSONObject jsonObject = GoWcf.goWCFService(URL);
                                //SendPostData sendpostdata = new SendPostData();
                                //sendpostdata.execute();
                            }
                            /*String url = null;
                            try {
                                url = URLEncoder.encode(URL, "UTF-8");
                                JSONObject jsonObject = GoWcf.goWCFService(url);
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }*/
                            //new TambahUser().execute();
                            //SendPostData sendpostdata = new SendPostData();
                            //sendpostdata.execute();

                        }
                    }
                });
                alertDialog.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }
        });
    }

    /*private class SendPostData extends AsyncTask<String, Void, String> {

        /*protected void onPreExecute() {
            try {
                super.onPreExecute();
                pDialog = new ProgressDialog(Registrasi.this);
                pDialog.setMessage("Registrasi User...");
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
       /* protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            /*HttpPost request = new HttpPost("http://192.168.43.141/ProjectWCF/User.svc/AddUser");
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");
            JSONStringer GetUserInfo;*/
            /*try {
                ttl = tgl + "-" + bln + "-" + thn;

                URL = "http://192.168.43.141/ProjectWCF/User.svc/AddUser/"
                        + username + "/" + jk + "/"
                        + nama + "/" + ttl + "/" + hp + "/" + email + "/"
                        + password + doj;
                String url = null;
                try {
                    url = URLEncoder.encode(URL, "UTF-8");
                    JSONObject jsonObject = GoWcf.goWCFService(URL);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }*/
                /*JSONObject jsonObject = GoWcf.goWCFService("http://192.168.43.141/ProjectWCF/User.svc/AddUser/"
                        + username + "/" + jk + "/"
                        + nama + "/" + tgl + "-" + bln + "-" + thn + "/" + hp + "/" + email + "/"
                        + password + "/" + doj);
               /* GetUserInfo = new JSONStringer()

                        .object()
                        .key("reguser")
                        .object()
                            .key("Ads_User").value(username)
                            .key("Ads_Gender").value(jk)
                            .key("Ads_Fullname").value(nama)
                            .key("Ads_Dob").value(ttl)
                            .key("Ads_PhoneNumber").value(hp)
                            .key("Ads_Email").value(email)
                            .key("Ads_Password").value(password)
                            .key("Ads_Doj").value(doj)
                            .endObject()
                            .endObject();

                StringEntity entity = new StringEntity(GetUserInfo.toString());
                Log.d("Test", GetUserInfo.toString());
                request.setEntity(entity);

                // Send request to WCF service
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpResponse response = httpClient.execute(request);
                Log.d("WebInvoke", "Saving : " + response.getStatusLine().getStatusCode());*/
            /*} catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }*/

    class TambahUser extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            try {
                super.onPreExecute();
                pDialog = new ProgressDialog(Registrasi.this);
                pDialog.setMessage("Registrasi User...");
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
                ttl = tgl + "-" + bln + "-" + thn;

                URL = "http://sahabatjam5.cloudapp.net/ProjectWCF/User.svc/AddUser/"
                        +username + "/" + jk + "/"
                        + nama + "/" + ttl + "/" + hp + "/" + email + "/"
                        + password + "/" + doj;
                /*String URL1 = username + "/" + jk + "/"
                        + nama + "/" + ttl + "/" + hp + "/" + email + "/"
                        + password + "/" + doj;*/
               JSONObject jsonObject = GoWcf.goWCFService(URL);
                /*String url;
                url = URLEncoder.encode(URL1, "UTF-8");
                JSONObject jsonObject = GoWcf.goWCFService(URL+url);*/
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String status) {
            pDialog.dismiss();
            finish();
            Toast.makeText(getApplicationContext(), "Berhasil Registrasi, Silahkan Login", Toast.LENGTH_LONG).show();
            Intent i = new Intent(Registrasi.this, Login.class);
            startActivity(i);
        }

    }

    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(Registrasi.this, Login.class);
        startActivity(i);
    }

}
