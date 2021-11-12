package com.example.logswasthfirebase;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;




public class AnalysisActivity extends AppCompatActivity {
    private Spinner selectSpinner;
    private ArrayAdapter selectAdapter;
    private String selectAnalysis;
    private Button numericalAnalysis,graphicalAnalysis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        selectSpinner =findViewById(R.id.analysed_parameter);
        selectAdapter=ArrayAdapter.createFromResource(this,R.array.parameter,R.layout.spinner_layout);
        selectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectSpinner.setAdapter(selectAdapter);
        selectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectAnalysis=selectSpinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        numericalAnalysis=(Button)findViewById(R.id.btnNa);
        graphicalAnalysis=(Button)findViewById(R.id.btnGv);

        numericalAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectAnalysis.equals("Select Parameter To Be Analysed")){
                    Toast.makeText(AnalysisActivity.this, getResources().getString(R.string.selectPara), Toast.LENGTH_SHORT).show();
                }
                else{
                    if(selectAnalysis.equals("Income")){
                        startActivity(new Intent(AnalysisActivity.this,Numerical_Analysis_Income.class));
                    }
                    else if(selectAnalysis.equals("Caste")){
                        startActivity(new Intent(AnalysisActivity.this,NumericalAnalysisCaste.class));
                    }
                    else if(selectAnalysis.equals("Disease")){
                        startActivity(new Intent(AnalysisActivity.this,NumericalAnalysis.class));
                    }
                    else if(selectAnalysis.equals("Education")){
                        startActivity(new Intent(AnalysisActivity.this,NumericalAnalysisEducation.class));
                    }
                    else{
                        startActivity(new Intent(AnalysisActivity.this,NumericalAnalysisAge.class));
                    }
                }
            }
        });

       graphicalAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectAnalysis.equals("Select Parameter To Be Analysed")){
                    Toast.makeText(AnalysisActivity.this, getResources().getString(R.string.selectPara), Toast.LENGTH_SHORT).show();
                }
                else{
                    if(selectAnalysis.equals("Income")){
                        startActivity(new Intent(AnalysisActivity.this,Incomeact.class));
                    }
                    else if(selectAnalysis.equals("Caste")){
                        startActivity(new Intent(AnalysisActivity.this,Caste.class));
                    }
                    else if(selectAnalysis.equals("Disease")){
                        startActivity(new Intent(AnalysisActivity.this,DiseaseDist.class));
                    }
                    else if(selectAnalysis.equals("Education")){
                        startActivity(new Intent(AnalysisActivity.this,Educationdist.class));
                    }
                    else{
                        startActivity(new Intent(AnalysisActivity.this,AgeDist.class));
                    }
                }
            }
        });


    }
}