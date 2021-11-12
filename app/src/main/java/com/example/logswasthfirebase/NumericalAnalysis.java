package com.example.logswasthfirebase;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NumericalAnalysis extends AppCompatActivity {
    private  TextView nd,cd,id,rd,od;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numerical_analysis);

        nd=(TextView)findViewById(R.id.tv4);
        cd=(TextView)findViewById(R.id.tv1);
        id=(TextView)findViewById(R.id.tv3);
        rd=(TextView)findViewById(R.id.tv2);
        od=(TextView)findViewById(R.id.tvOthers);

        FirebaseDatabase rootNode;
        DatabaseReference reference;
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("villagers");

        ArrayList<String> dislist = new ArrayList<String>();

        int [] disease = new int[6];
        for(int i =0; i<6; i++)
        {
            disease[i]=0;
        }
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot mydataSnapshot : dataSnapshot.getChildren()) {
                    VillagerHelperClass villagersdetails = mydataSnapshot.getValue(VillagerHelperClass.class);
                    dislist.add(villagersdetails.getDisease());

                }


                for (String s : dislist) {
                    if (s.equals("Cardiovascular Diseases")) {
                        disease[1]++;
                    }
                    if (s.equals("Respiratory Diseases")) {
                        disease[2]++;
                    }
                    if (s.equals("Intestinal Diseases")) {
                        disease[3]++;
                    }
                    if (s.equals("Neurological disorders")) {
                        disease[4]++;
                    }
                    if (s.equals("Others")) {
                        disease[5]++;
                    }
                }
                nd.setText(disease[4]+"");
                od.setText(disease[5]+"");
                id.setText(disease[3]+"");
                cd.setText(disease[1]+"");
                rd.setText(disease[2]+"");

            }



            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


    }
}