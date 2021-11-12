package com.example.logswasthfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NumericalAnalysisCaste extends AppCompatActivity {
    private TextView tc1,tc2,tc3,tc4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numerical_analysis_caste);

        tc1=(TextView)findViewById(R.id.tCaste1);
        tc2=(TextView)findViewById(R.id.tCaste2);
        tc3=(TextView)findViewById(R.id.tCaste3);
        tc4=(TextView)findViewById(R.id.tCaste4);

        FirebaseDatabase rootNode;
        DatabaseReference reference;
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("villagers");

        ArrayList<String> castelist = new ArrayList<String>();

        int [] caste = new int[5];
        for(int i =0; i<5; i++)
        {
            caste[i]=0;
        }
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot mydataSnapshot : dataSnapshot.getChildren()) {
                    VillagerHelperClass villagersdetails = mydataSnapshot.getValue(VillagerHelperClass.class);
                    castelist.add(villagersdetails.getCaste());
                }


                for (String s : castelist) {
                    if (s.equals("General")) {
                        caste[1]++;
                    }
                    if (s.equals("Scheduled Caste(SC)")) {
                        caste[2]++;
                    }
                    if (s.equals("Scheduled Tribes(ST)")) {
                        caste[3]++;
                    }
                    if (s.equals("Other Backward Classes(OBC)")) {
                        caste[4]++;
                    }

                }
                tc1.setText(caste[1]+"");
                tc2.setText(caste[2]+"");
                tc3.setText(caste[3]+"");
                tc4.setText(caste[4]+"");



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
}}