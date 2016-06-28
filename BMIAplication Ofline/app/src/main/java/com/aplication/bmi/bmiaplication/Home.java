package com.aplication.bmi.bmiaplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Ugi Ispoyo Widodo on 5/18/2016.
 */
public class Home extends AppCompatActivity {
    SharedPreferences session;
    TextView tp_user;
    //Button help, about, set, rec, bmi, bw, logout;
    ImageView help, about, set, rec, bmi, bw, logout;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        session = getSharedPreferences("session", MODE_PRIVATE);
        /*help = (Button) findViewById(R.id.btn_help);
        about = (Button) findViewById(R.id.btn_about);
        //set = (Button) findViewById(R.id.btn_set);
        rec = (Button) findViewById(R.id.btn_rec);
        bmi = (Button) findViewById(R.id.btn_bmi);
        bw = (Button) findViewById(R.id.btn_bw);
        logout = (Button) findViewById(R.id.btn_logout);*/
        help = (ImageView) findViewById(R.id.btn_help);
        about = (ImageView) findViewById(R.id.btn_about);
        //set = (Button) findViewById(R.id.btn_set);
        rec = (ImageView) findViewById(R.id.btn_rec);
        bmi = (ImageView) findViewById(R.id.btn_bmi);
        bw = (ImageView) findViewById(R.id.btn_bw);
        logout = (ImageView) findViewById(R.id.btn_logout);

        tp_user = (TextView) findViewById(R.id.user);
        user = session.getString("nama", null);
        tp_user.setText(user);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Home.this);
                alertDialog.setTitle("Logout Aplikasi?");
                alertDialog.setMessage("Apakah Anda Ingin Logout ?");
                alertDialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor editor = session.edit();
                        editor.remove("nama");
                        editor.remove("nilai");
                        editor.commit();
                        finish();
                        Intent i = new Intent(Home.this, Login.class);
                        startActivity(i);
                    }
                });
                alertDialog.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(Home.this, Help.class);
                startActivity(i);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(Home.this, About.class);
                startActivity(i);
            }
        });
        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(Home.this, BMI.class);
                startActivity(i);
            }
        });
        rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(Home.this, Record.class);
                startActivity(i);
            }
        });
       /* set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(Home.this, Setting.class);
                startActivity(i);
            }
        });*/
        bw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(Home.this, TipsActivity.class);
                startActivity(i);
            }
        });
    }

}
