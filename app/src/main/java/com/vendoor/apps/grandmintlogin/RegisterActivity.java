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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
EditText editName,editEmail,editPassword;
    Button submitButton;
    private FirebaseAuth mAuth;
    public SharedPreferences sharedPreferences;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editName=(EditText)findViewById(R.id.editName);
        editEmail=(EditText)findViewById(R.id.editEmail);
        editPassword=(EditText)findViewById(R.id.editPassword);
        submitButton=(Button)findViewById(R.id.submitButton);
        mAuth=FirebaseAuth.getInstance();
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.createUserWithEmailAndPassword(editEmail.getText().toString(), editPassword.getText().toString())
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    Log.e("TAG", "onComplete: ");
                                    Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(i);
                                } else {Log.e("TAG", "failed: ");}
                            }
                        });
                FirebaseAuth.getInstance().getCurrentUser()
                        .sendEmailVerification()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    Log.e("TAG", "Successful: ");
                                }
                            }
                        });
            }
        });
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("name",editName.getText().toString());
        editor.commit();
    }

}

