package com.example.logswasthfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;

import static android.graphics.Color.MAGENTA;
import static android.graphics.Color.RED;

public class Caste extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caste);

        FirebaseDatabase rootNode;
        DatabaseReference reference;
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("villagers");

        GraphView graph=(GraphView)findViewById(R.id.graph);
        BarGraphSeries<DataPoint> series= new BarGraphSeries<>();
        DataPoint[] dp = new DataPoint[4];



        ArrayList<String> castelist = new ArrayList<String>();

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
                    castelist.add(villagersdetails.getCaste());


                }

                for(String i : castelist)
                {
                    if(i.equals("General"))
                    {
                        arr[0]++;
                    }
                    if(i.equals("Scheduled Caste(SC)"))
                    {
                        arr[1]++;
                    }
                    if(i.equals("Scheduled Tribes(ST)"))
                    {
                        arr[2]++;
                    }
                    if(i.equals("Other Backward Classes(OBC)"))
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
                series.setDrawValuesOnTop(true);
                series.setSpacing(5);
                graph.getViewport().setMinX(1);
                graph.getViewport().setMinY(0);
                graph.getViewport().setMaxX(4);
                graph.setTitle("Caste Distribution");
                graph.getViewport().setXAxisBoundsManual(true);
                GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
                gridLabel.setHorizontalAxisTitle("Caste");
                StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
                staticLabelsFormatter.setHorizontalLabels(new String[] {"General", "SC", "ST", "OBC"});

                graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
            }



            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

    }
}