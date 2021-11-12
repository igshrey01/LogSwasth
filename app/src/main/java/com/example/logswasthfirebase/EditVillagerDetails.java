package com.example.logswasthfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EditVillagerDetails extends AppCompatActivity {
    private Button btProceed;
    private EditText etAadhar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_villager_details);
        DatabaseReference root = FirebaseDatabase.getInstance().getReference();
        DatabaseReference users = root.child("villagers");

        btProceed=(Button)findViewById(R.id.btnEditProceed);
        etAadhar=(EditText)findViewById(R.id.eteditEnterAadhar);

        btProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ArrayList<String> f=new ArrayList<String>();
                String aadhar=etAadhar.getText().toString();
                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.child(aadhar).exists()) {
                            Toast.makeText(EditVillagerDetails.this, getResources().getString(R.string.correctAadhar), Toast.LENGTH_SHORT).show();
                            String path = "villagers/"+aadhar;
                            FirebaseDatabase database=FirebaseDatabase.getInstance();
                            DatabaseReference reference=database.getReference(path);
                            reference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    int age=0;
                                    String education,disease,caste,district,income,state;
                                    age=snapshot.child("age").getValue(int.class);
                                    education=snapshot.child("education").getValue(String.class);

                                    disease=snapshot.child("disease").getValue(String.class);
                                    caste=snapshot.child("caste").getValue(String.class);
                                    district=snapshot.child("district").getValue(String.class);


                                    income=snapshot.child("income").getValue(String.class);
                                    state=snapshot.child("state").getValue(String.class);
//                                    Log.v("dii",district);
//                                    Log.v("ca",caste);
//                                    Log.v("st",state);
//                                    Log.v("age",Integer.toString(age));
//                                    Log.v("di",disease);
//                                    Log.v("edu",education);
//                                    Log.v("in",income);
                                    Bundle bundle= new Bundle();
                                    bundle.putString("Aadhar",aadhar);
                                    bundle.putString("Age",Integer.toString(age));
                                    bundle.putString("Education",education);
                                    bundle.putString("State",state);
                                    bundle.putString("District",district);
                                    bundle.putString("Caste",caste);
                                    bundle.putString("Income",income);
                                    bundle.putString("Disease",disease);
                                    Intent intent = new Intent(EditVillagerDetails.this,EditVillager2.class);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                        }else{
                            Toast.makeText(EditVillagerDetails.this, getResources().getString(R.string.enterAadharnumb), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                }


        });
    }
}