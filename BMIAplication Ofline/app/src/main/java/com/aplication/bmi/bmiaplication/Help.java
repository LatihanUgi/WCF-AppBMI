package com.aplication.bmi.bmiaplication;

import android.app.ProgressDialog;
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
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
/**
 * Created by Ugi Ispoyo Widodo on 5/18/2016.
 */
public class Help extends AppCompatActivity {
    SharedPreferences session;
    String user;
    TextView tp_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        tp_user = (TextView) findViewById(R.id.user);
        session = getSharedPreferences("session", MODE_PRIVATE);
        user = session.getString("nama", null);
        tp_user.setText(user);
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(Help.this, Home.class);
        startActivity(i);
    }

}
