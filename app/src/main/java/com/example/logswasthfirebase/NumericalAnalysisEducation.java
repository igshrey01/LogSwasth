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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class NumericalAnalysisEducation extends AppCompatActivity {
    private TextView te1,te2,te3,te4,te5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numerical_analysis_education);

        te1=(TextView)findViewById(R.id.ted1);
        te2=(TextView)findViewById(R.id.ted2);
        te3=(TextView)findViewById(R.id.ted3);
        te4=(TextView)findViewById(R.id.ted4);
        te5=(TextView)findViewById(R.id.ted5);

        FirebaseDatabase rootNode;
        DatabaseReference reference;
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("villagers");

        ArrayList<String> edulist = new ArrayList<String>();

        int [] edu = new int[6];
        for(int i =0; i<6; i++)
        {
            edu[i]=0;
        }
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot mydataSnapshot : dataSnapshot.getChildren()) {
                    VillagerHelperClass villagersdetails = mydataSnapshot.getValue(VillagerHelperClass.class);
                    edulist.add(villagersdetails.getEducation());

                }


                for (String s : edulist) {
                    if (s.equals("Higher Secondary Certificate")) {
                        edu[1]++;
                    }
                    if (s.equals("Senior Secondary Certificate")) {
                        edu[2]++;
                    }
                    if (s.equals("Undergraduation")) {
                        edu[3]++;
                    }
                    if (s.equals("Postgraduation")) {
                        edu[4]++;
                    }
                    if (s.equals("Other")) {
                        edu[5]++;
                    }

                }
                te1.setText(edu[1]+"");
                te2.setText(edu[2]+"");
                te3.setText(edu[3]+"");
                te4.setText(edu[4]+"");
                te5.setText(edu[5]+"");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}