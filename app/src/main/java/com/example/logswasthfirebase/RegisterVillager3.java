package com.example.logswasthfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterVillager3 extends AppCompatActivity {
    private Spinner casteSpinner,diseaseSpinner,incomeSpinner;
    private String selectCaste,selectDisease,selectIncome;
    private ArrayAdapter<CharSequence> casteAdapter,incomeAdapter,diseaseAdapter;
    private Button submit;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_villager3);
        progressDialog=new ProgressDialog(this);

        submit=(Button)findViewById(R.id.btnFinalSubmit);
        Bundle bundle= getIntent().getExtras();
        long aadhar=bundle.getLong("Aadhar");
        int age=bundle.getInt("Age");
        String edu=bundle.getStringArrayList("key").get(2);
        String state=bundle.getStringArrayList("key").get(0);
        String district=bundle.getStringArrayList("key").get(1);
        //Log.v("aadhar",String.valueOf(aadhar));
//        Log.v("Age",String.valueOf(age));
//        Log.v("edu",edu);
//        Log.v("state",state);
//        Log.v("district",district);
        FirebaseDatabase rootNode;
        DatabaseReference reference;
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("villagers");

        casteSpinner=findViewById(R.id.socio_economic);
        casteAdapter=ArrayAdapter.createFromResource(this,R.array.socio_economic_status,R.layout.spinner_layout);
        casteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        casteSpinner.setAdapter(casteAdapter);
        casteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectCaste=casteSpinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        incomeSpinner=findViewById(R.id.income);
        incomeAdapter=ArrayAdapter.createFromResource(this,R.array.income1,R.layout.spinner_layout);
        incomeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        incomeSpinner.setAdapter(incomeAdapter);
        incomeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectIncome=incomeSpinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        diseaseSpinner=findViewById(R.id.disease);
        diseaseAdapter=ArrayAdapter.createFromResource(this,R.array.disease_domain,R.layout.spinner_layout);

        diseaseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        diseaseSpinner.setAdapter(diseaseAdapter);
        diseaseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectDisease=diseaseSpinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Uploading Data To Cloud");
                progressDialog.show();
                if("Select Caste".equals(selectCaste) || "Select Disease".equals(selectDisease) ||"Select Income".equals(selectIncome)){
                    progressDialog.dismiss();
                    Toast.makeText(RegisterVillager3.this, getResources().getString(R.string.enterAll), Toast.LENGTH_SHORT).show();
                }
                else{

                    Log.v("aadhar",String.valueOf(aadhar));
                    Log.v("Age",String.valueOf(age));
                    Log.v("edu",edu);
                    Log.v("state",state);
                    Log.v("district",district);
                    Log.v("Caste",selectCaste);
                    Log.v("Disease",selectDisease);
                    Log.v("Income",selectIncome);

                    //aadhar - long
                    //age - int
                    //edu = string
                    //state = string
                    //district = string
                    //selectCaste = string
                    //selectDisease = string
                    //selectIncome = string


                    VillagerHelperClass helperClass = new VillagerHelperClass(edu, state, district, selectIncome, selectCaste, selectDisease,age);
                    reference.child(String.valueOf(aadhar)).setValue(helperClass);
                    progressDialog.dismiss();
                    Toast.makeText(RegisterVillager3.this, getResources().getString(R.string.updateCloud), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterVillager3.this,HomePage1.class));

                }
            }
        });





    }
}