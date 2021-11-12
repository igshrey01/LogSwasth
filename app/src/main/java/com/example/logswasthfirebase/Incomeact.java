package com.example.logswasthfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class Incomeact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incomeact);

        FirebaseDatabase rootNode;
        DatabaseReference reference;
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("villagers");

        GraphView graph=(GraphView)findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series= new LineGraphSeries<>();
        DataPoint[] dp = new DataPoint[4];



        ArrayList<String> Incomelist = new ArrayList<String>();

        int [] arr= new int[4];

        for(int i =0; i<4; i++)
        {
            arr[i]=0;
        }

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot mydataSnapshot : dataSnapshot.getChildren()){
                    VillagerHelperClass villagersdetails = mydataSnapshot.getValue(VillagerHelperClass.class);
                    Incomelist.add(villagersdetails.getIncome());

                }

                for(String i : Incomelist)
                {
                    if(i.equals("Below 10000"))
                    {
                        arr[0]++;
                    }
                    if(i.equals("10000-50000"))
                    {
                        arr[1]++;
                    }
                    if(i.equals("50000-150000"))
                    {
                        arr[2]++;
                    }
                    if(i.equals("Above 150000"))
                    {
                        arr[3]++;
                    }

                }

                dp[0]= new DataPoint(1,arr[0]);
                dp[1]= new DataPoint(2,arr[1]);
                dp[2]= new DataPoint(3,arr[2]);
                dp[3]= new DataPoint(4,arr[3]);

                series.resetData(dp);
                graph.addSeries(series);
                series.setDrawBackground(true);
                graph.getViewport().setMinY(0);
                series.setDrawDataPoints(true);
                graph.getViewport().setMinX(1);
                graph.getViewport().setMaxX(4);
                graph.setTitle("Income Distribution");
                graph.getViewport().setXAxisBoundsManual(true);
                StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
                staticLabelsFormatter.setHorizontalLabels(new String[] {"<10k", "10k-50k", "50k-150k", "150k>"});
                GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
                gridLabel.setHorizontalAxisTitle("income");
                graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });



    }
}