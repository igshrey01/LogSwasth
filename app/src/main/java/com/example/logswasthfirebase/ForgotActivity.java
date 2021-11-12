package com.example.logswasthfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotActivity extends AppCompatActivity {
    private Button resetPassword;
    private EditText reEmail;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        resetPassword=(Button)findViewById(R.id.btnResetPassword);
        reEmail=(EditText)findViewById(R.id.etfEmail);
        firebaseAuth=FirebaseAuth.getInstance();
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=reEmail.getText().toString();
                if(s1.isEmpty()){
                    Toast.makeText(ForgotActivity.this, getResources().getString(R.string.detailCorrect), Toast.LENGTH_SHORT).show();
                }
                else{
                    firebaseAuth.sendPasswordResetEmail(s1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ForgotActivity.this, getResources().getString(R.string.resetLink), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ForgotActivity.this,MainActivity.class));
                            }
                            else{
                                Toast.makeText(ForgotActivity.this, getResources().getString(R.string.noresetLink), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}