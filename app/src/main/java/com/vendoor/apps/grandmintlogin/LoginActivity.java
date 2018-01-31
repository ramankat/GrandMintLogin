package com.vendoor.apps.grandmintlogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText LeditEmail,LeditPassword;
    Button loginButton,verifyButton;
    private FirebaseAuth firebaseAuth;
    public SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton=(Button)findViewById(R.id.LsubmitButton);
        verifyButton=(Button)findViewById(R.id.LverifyButton);
        LeditEmail=(EditText)findViewById(R.id.LeditEmail);
        LeditPassword=(EditText)findViewById(R.id.LeditPassword);
        firebaseAuth = FirebaseAuth.getInstance();
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (firebaseAuth.signInWithEmailAndPassword(LeditEmail.getText().toString(), LeditPassword.getText().toString()))
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(LoginActivity.this, Home.class);
                                    i.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
                                    startActivity(i);
                                } else {
                                    Log.e("ERROR", task.getException().toString());
                                    Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                                }
                            }
                        });
            }
        });
        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIfEmailVerified();
            }
        });
    }
    private void checkIfEmailVerified()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user.isEmailVerified())
        {
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putBoolean("verified",true);
            editor.commit();
        }
        else
        {
            // email is not verified, so just prompt the message to the user and restart this activity.
            // NOTE: don't forget to log out the user.
            FirebaseAuth.getInstance().signOut();

            //restart this activity

        }
    }
}
