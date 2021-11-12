package com.example.logswasthfirebase;

import androidx.appcompat.app.AppCompatActivity;

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

public class EditVillager3 extends AppCompatActivity {
    private Spinner casteSpinner,diseaseSpinner,incomeSpinner;
    private String selectCaste,selectDisease,selectIncome;
    private ArrayAdapter<CharSequence> casteAdapter,incomeAdapter,diseaseAdapter;
    private Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_villager3);
        Bundle bundle = getIntent().getExtras();
        String caste=bundle.getString("Caste");
        String disease=bundle.getString("Disease");
        String income=bundle.getString("Income");
        String aadhar=bundle.getString("Aadhar");

        update=(Button)findViewById(R.id.btnFinalSubmit);
        casteSpinner=findViewById(R.id.socio_economic);
        casteAdapter=ArrayAdapter.createFromResource(this,R.array.socio_economic_status,R.layout.spinner_layout);
        casteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        casteSpinner.setAdapter(casteAdapter);
        casteSpinner.setSelection(casteAdapter.getPosition(caste));
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
        incomeSpinner.setSelection(incomeAdapter.getPosition(income));
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
        diseaseSpinner.setSelection(diseaseAdapter.getPosition(disease));
        diseaseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectDisease=diseaseSpinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("Select Caste".equals(selectCaste) || "Select Disease".equals(selectDisease) || "Select Income".equals(selectIncome)) {
                    Toast.makeText(EditVillager3.this, getResources().getString(R.string.enterAll), Toast.LENGTH_SHORT).show();
                }
                else{
                    String path = "villagers/"+aadhar;
                    FirebaseDatabase database=FirebaseDatabase.getInstance();
                    DatabaseReference reference=database.getReference(path);

                    if(selectDisease!=disease){
                        reference.child("disease").setValue(selectDisease);
                    }
                    if(selectIncome!=income){
                        reference.child("income").setValue(selectIncome);
                    }
                    if(selectCaste!=caste){
                        reference.child("caste").setValue(selectCaste);
                    }
                    Toast.makeText(EditVillager3.this,getResources().getString(R.string.updateCloud),Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(EditVillager3.this,HomePage1.class));
                }
            }
        });
    }
}