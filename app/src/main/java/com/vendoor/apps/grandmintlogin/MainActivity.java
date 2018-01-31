package com.vendoor.apps.grandmintlogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
public SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        String name=sharedPreferences.getString("name","");
        boolean verified=sharedPreferences.getBoolean("verified",false);
        if(!name.equals("") && verified)
        {
            Intent i = new Intent(MainActivity.this, Home.class);
            startActivity(i);
        }
        Intent i = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(i);
    }

}
