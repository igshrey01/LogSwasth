package com.example.logswasthfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

public class
MainActivity extends AppCompatActivity {
    private Button login,reset,changeLangu;
    private EditText userEmail,userPassword;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        loadLocale();
        progressDialog=new ProgressDialog(this);
        setContentView(R.layout.activity_main);
        login=(Button)findViewById(R.id.btnLogin);
        userEmail=(EditText) findViewById(R.id.etEmail);
        userPassword=(EditText)findViewById(R.id.etPassword);
        firebaseAuth=FirebaseAuth.getInstance();
        reset=(Button)findViewById(R.id.btnReset);
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser!=null)
        {
            startActivity(new Intent(MainActivity.this,HomePage1.class));
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));


        changeLangu=(Button)findViewById(R.id.btnChangeLang2);
        changeLangu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeLanguageDialog();
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ForgotActivity.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage(getResources().getString(R.string.signin));
                progressDialog.show();
                String s1=userEmail.getText().toString();
                String s2=userPassword.getText().toString();

                if(s1.isEmpty() || s2.isEmpty()){
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.enterAll), Toast.LENGTH_SHORT).show();
                }
                else{
                    validate(s1,s2);
                }
            }
        });
    }
    private void validate(String useremail, String userpassword)
    {
        firebaseAuth.signInWithEmailAndPassword(useremail,userpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {

                    Toast.makeText(MainActivity.this,getResources().getString(R.string.loginsuccess) ,Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(MainActivity.this,HomePage1.class);
                    startActivity(intent);
                    progressDialog.dismiss();
                }
                else
                {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.loginunsuccess), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });
    }
    private void showChangeLanguageDialog() {
        final String[] listItems={"हिंदी","मराठी","తెలుగు","বাংলা","English"};
        AlertDialog.Builder mBuilder= new AlertDialog.Builder(MainActivity.this);
        mBuilder.setTitle("Change Language...");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(i==0){
                    setLocale("hi");
                    recreate();
                }
                else if(i==1){
                    setLocale("mr");
                    recreate();
                }
                else if(i==4){
                    setLocale("en");
                    recreate();
                }
                else if(i==2){
                    setLocale("te");
                    recreate();
                }
                else if(i==3){
                    setLocale("bn");
                    recreate();
                }
                dialog.dismiss();
            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale=locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor=getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();
    }
    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang","");
        setLocale(language);
    }
}