package com.aplication.bmi.bmiaplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.json.JSONObject;

/**
 * Created by Ugi Ispoyo Widodo on 5/18/2016.
 */
public class BMI extends AppCompatActivity {
    private ProgressDialog pDialog;
    SharedPreferences session;
    TextView tp_user, jwb1, jwb2, jwb3;
    EditText tinggi, berat;
    Button btn_proses;
    String user, date, URL, ketBerat, Tinggi, Berat, tampil_bmi, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi);
        session = getSharedPreferences("session", MODE_PRIVATE);
        DateFormat df = new SimpleDateFormat("d-MMM-yyyy");
        date = df.format(Calendar.getInstance().getTime());
        //DateFormat df1 = new SimpleDateFormat("HH:mm");
        //minute = df1.format(Calendar.getInstance().getTime());

        tp_user = (TextView) findViewById(R.id.user);
        jwb1 = (TextView) findViewById(R.id.tv_jwb1);
        jwb2 = (TextView) findViewById(R.id.tv_jwb2);
        jwb3 = (TextView) findViewById(R.id.tv_jwb3);
        tinggi = (EditText) findViewById(R.id.et_tinggi);
        berat = (EditText) findViewById(R.id.et_berat);
        btn_proses = (Button) findViewById(R.id.hitung_bmi);
        user = session.getString("nama", null);
        tp_user.setText(user);

        btn_proses.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {

                                              float hitung_tinggi = Float.parseFloat(tinggi.getText().toString());
                                              float hitung_berat = Float.parseFloat(berat.getText().toString());
                                              float bbi = (hitung_tinggi - 100) * 0.9f;

                                              //hitung bmi (body mass index) dengan rumus
                                              //BMI = (BB) / [(TB) * (TB)]
                                              //keterangan
                                              //BMI < 18.5 = berat badan kurang (underweight)
                                              //BMI 18.5 - 24 = normal
                                              //BMI 25 - 29 = kelebihan berat badan (overweight)
                                              //BMI >30 = obesitas
                                              float tinggiMeter = hitung_tinggi / 100;
                                              float bmi = hitung_berat / (tinggiMeter * tinggiMeter);
                                              if (bmi < 18.5) {
                                                  ketBerat = "Kurus";
                                              } else if (bmi >= 18.5 && bmi < 25) {
                                                  ketBerat = "Normal";
                                              } else if (bmi >= 25 && bmi < 30) {
                                                  ketBerat = "Gemuk";
                                              } else {
                                                  ketBerat = "Obesitas";
                                              }


                                              String tips;
                                              if (ketBerat.equals("Kurus"))
                                              {
                                                  tips = "- Perbanyak konsumsi protein dan lemak\n" +
                                                          "- Kurangi aktivitas yang mengurangi waktu tidur (begadang)\n" +
                                                          "- Olahraga rutin dan konsumsi air mineral";
                                              }
                                              else if(ketBerat.equals("Normal"))
                                              {
                                                  tips = "- Lakukan olahraga yang dapat membentuk massa otot\n" +
                                                          "- Rutin konsumsi protein dan lemak\n" +
                                                          "- Konsumsi air mineral sesuai kebutuhan";
                                              }
                                              else if (ketBerat.equals("Gemuk"))
                                              {
                                                  tips = "- Lakukan olahraga yang membakar lemak\n" +
                                                          "- Kurangi mengkonsumsi makanan cepat saji\n" +
                                                          "- Istirahat secukupnya";
                                              }
                                              else
                                              {
                                                  tips = "- Lakukan olahraga yang membakar lemak\n" +
                                                          "- Kurangi mengkonsumsi makanan cepat saji dan lemak berlebih\n" +
                                                          "- Istirahat secukupnya";
                                              }
                                              tampil_bmi = String.valueOf(bbi);
                                              jwb1.setText(tampil_bmi);

                                              jwb2.setText(ketBerat);
                                              jwb3.setText(tips);

                                              Tinggi = String.valueOf(hitung_tinggi);
                                              Berat = String.valueOf(hitung_berat);

                                              ConnectionDetector cd;
                                              cd = new ConnectionDetector(getApplicationContext());
                                              if (!cd.isConnectingToInternet()) {
                                                  Toast.makeText(getApplicationContext(), "Tidak Ada Koneksi Ke Server", Toast.LENGTH_LONG).show();
                                                  setContentView(R.layout.bmi);
                                              } else {
                                                  new TambahRecord().execute();
                                                  /*int SDK_INT = android.os.Build.VERSION.SDK_INT;
                                                  if (SDK_INT > 8) {
                                                      StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                                                              .permitAll().build();
                                                      StrictMode.setThreadPolicy(policy);
                                                      new TambahRecord().execute();
                                                  }*/
                                              }
                                          }
                                      }

        );
    }

    class TambahRecord extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            try {
                super.onPreExecute();
                pDialog = new ProgressDialog(BMI.this);
                pDialog.setMessage("Sedang Menghitung ...");
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
                URL = "http://192.168.43.141/ProjectWCF/User.svc/AddRecord/"
                        + user + "/" + ketBerat + "/"
                        + tampil_bmi + "/" + Tinggi + "/" + Berat + "/" + date;
                JSONObject jsonObject = GoWcf.goWCFService(URL);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String status) {
            pDialog.dismiss();
        }

    }

    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(BMI.this, Home.class);
        startActivity(i);
    }

}
