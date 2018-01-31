package com.vendoor.apps.grandmintlogin;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    public SharedPreferences sharedPreferences;
    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
     name=(TextView)findViewById(R.id.name);
        name.setText(sharedPreferences.getString("name",""));
    }
}
