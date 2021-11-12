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

public class NumericalAnalysisAge extends AppCompatActivity {
    private TextView tt1,tt2,tt3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numerical_analysis_age);

        tt1=(TextView)findViewById(R.id.tvv1);
        tt2=(TextView)findViewById(R.id.tvv2);
        tt3=(TextView)findViewById(R.id.tvv3);

        FirebaseDatabase rootNode;
        DatabaseReference reference;
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("villagers");

        ArrayList<Integer> agelsit = new ArrayList<Integer>();
        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = 0;
        }

        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot mydataSnapshot : dataSnapshot.getChildren()) {
                    VillagerHelperClass villagersdetails = mydataSnapshot.getValue(VillagerHelperClass.class);
                    agelsit.add(villagersdetails.getAge());
                }

                for (int i : agelsit) {
                    if(i<18){
                        arr[1]++;
                    }
                    else if(i>=18 && i<=65){
                        arr[2]++;
                    }
                    else{
                        arr[3]++;
                    }

                }
                tt1.setText(arr[1]+"");
                tt2.setText(arr[2]+"");
                tt3.setText(arr[3]+"");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }}