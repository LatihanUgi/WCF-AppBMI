package com.aplication.bmi.bmiaplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by WIN 8.1 on 12/12/2015.
 */
public class Login extends AppCompatActivity {
    SharedPreferences session;
    SharedPreferences.Editor editor;
    private ProgressDialog pDialog;
    EditText username_i, password_i;
    Button login, register;
    String user, password;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        session = getSharedPreferences("session", MODE_PRIVATE);
        editor = session.edit();
        int n = session.getInt("nilai", 0);
        username_i = (EditText) findViewById(R.id.et_username);
        password_i = (EditText) findViewById(R.id.et_password);
        login = (Button) findViewById(R.id.btn_login);
        register = (Button) findViewById(R.id.btn_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(Login.this, Registrasi.class);
                startActivity(i);
            }
        });
        ConnectionDetector cd;
        cd = new ConnectionDetector(getApplicationContext());
        if (!cd.isConnectingToInternet()) {
            Toast.makeText(getApplicationContext(), "Tidak Ada Koneksi Ke Server", Toast.LENGTH_LONG).show();
            setContentView(R.layout.login);
        } else {
            if (n == 1) {
                finish();
                Intent i = new Intent(Login.this, Home.class);
                startActivity(i);
            }
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectionDetector cd1;
                cd1 = new ConnectionDetector(getApplicationContext());
                if (!cd1.isConnectingToInternet()) {
                    Toast.makeText(getApplicationContext(), "Tidak Ada Koneksi Ke Server", Toast.LENGTH_LONG).show();
                    setContentView(R.layout.login);
                } else {
                    user = username_i.getText().toString();
                    password = password_i.getText().toString();
                    new LoginProses().execute();
                    //Toast.makeText(getApplicationContext(), "http://sahabatjam5.cloudapp.net/ProjectWCF/User.svc/Login?username="+user+"&password="+password, Toast.LENGTH_LONG).show();
                    /*if(verifyLogin(user,password))
                    {
                        int n = 1;
                        editor.putString("nama", user);
                        editor.putInt("nilai", n);
                        editor.commit();
                        finish();
                        Intent i = new Intent(Login.this, Home.class);
                        startActivity(i);
                    }else {
                        Toast.makeText(getApplicationContext(), "Username & Password Anda Salah!", Toast.LENGTH_LONG).show();
                        //Toast.makeText(getApplicationContext(), "http://sahabatjam5.cloudapp.net/ProjectWCF/User.svc/Login?username="+user+"&password="+password, Toast.LENGTH_LONG).show();
                    }*/
                }
            }
        });
    }


    class LoginProses extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            try {
                super.onPreExecute();
                pDialog = new ProgressDialog(Login.this);
                pDialog.setMessage("Login User...");
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
                if (verifyLogin(user, password)) {
                    return "1";
                }
                else
                {
                    return "0";
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String status) {
            pDialog.dismiss();
            if(status.equals("0")) {
                Toast.makeText(getApplicationContext(), "Username & Password Anda Salah!", Toast.LENGTH_LONG).show();
            }
            else
            {
                int n = 1;
                editor.putString("nama", user);
                editor.putInt("nilai", n);
                editor.commit();
                finish();
                Intent i = new Intent(Login.this, Home.class);
                startActivity(i);
            }
        }

    }

    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static boolean verifyLogin(String user, String password) {
        try {
            //String URL = "http://sahabatjam5.cloudapp.net/ProjectWCF/User.svc/Login/"+user+"/"+password;
            DefaultHttpClient httpClient = new DefaultHttpClient();

            //Connect to the server
            //here the 10.0.2.2 is the local host
            HttpGet httpGet = new HttpGet("http://192.168.43.141/ProjectWCF/User.svc/Login?username=" + user + "&password=" + password);
            //Get the response
            httpGet.setHeader("Accept", "application/json");
            httpGet.setHeader("Content-type", "application/json");

            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            InputStream stream = httpEntity.getContent();

            //Convert the stream to readable format
            String result = convertStreamToString(stream);

            if (result.charAt(1) == '1') {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Login.this);
        alertDialog.setTitle("Konfirmasi Keluar");
        alertDialog.setMessage("Apakah Anda Ingin Keluar ?");
        alertDialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alertDialog.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }
}
