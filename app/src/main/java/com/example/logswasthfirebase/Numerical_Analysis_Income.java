package com.example.logswasthfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Numerical_Analysis_Income extends AppCompatActivity {
    private TextView t1,t2,t3,t4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numerical__analysis__income);

        t1=(TextView)findViewById(R.id.tv1);
        t2=(TextView)findViewById(R.id.tv2);
        t3=(TextView)findViewById(R.id.tv3);
        t4=(TextView)findViewById(R.id.tv4);

        FirebaseDatabase rootNode;
        DatabaseReference reference;
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("villagers");

        ArrayList<String> incomelist = new ArrayList<String>();

        int [] income = new int[5];
        for(int i =0; i<5; i++)
        {
            income[i]=0;
        }
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot mydataSnapshot : dataSnapshot.getChildren()) {
                    VillagerHelperClass villagersdetails = mydataSnapshot.getValue(VillagerHelperClass.class);
                    incomelist.add(villagersdetails.getIncome());

                }


                for (String s : incomelist) {
                    if (s.equals("Below 10000")) {
                        income[1]++;
                    }
                    if (s.equals("10000-50000")) {
                        income[2]++;
                    }
                    if (s.equals("50000-150000")) {
                        income[3]++;
                    }
                    if (s.equals("Above 150000")) {
                        income[4]++;
                    }

                }
                t1.setText(income[1]+"");
                t2.setText(income[2]+"");
                t3.setText(income[3]+"");
                t4.setText(income[4]+"");



            }



            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
}