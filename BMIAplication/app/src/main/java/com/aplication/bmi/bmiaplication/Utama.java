package com.aplication.bmi.bmiaplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Utama extends AppCompatActivity {
    SharedPreferences session;
    SharedPreferences.Editor editor;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(5000);
                    finish();
                    Intent i = new Intent(Utama.this, Login.class);
                    startActivity(i);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }
}
