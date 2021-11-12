package com.example.logswasthfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.graphics.Color.MAGENTA;
import static android.graphics.Color.RED;

public class AgeDist extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_dist);

        FirebaseDatabase rootNode;
        DatabaseReference reference;
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("villagers");

        GraphView graph=(GraphView)findViewById(R.id.graph);
        BarGraphSeries<DataPoint> series= new BarGraphSeries<>();
        DataPoint[] dp = new DataPoint[10];



        ArrayList<Integer> agelsit = new ArrayList<Integer>();

        int [] arr= new int[10];

        for(int i =0; i<10; i++)
        {
            arr[i]=0;
        }

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot mydataSnapshot : dataSnapshot.getChildren()){
                    VillagerHelperClass villagersdetails = mydataSnapshot.getValue(VillagerHelperClass.class);
                    agelsit.add(villagersdetails.getAge());


                }

                for(int i : agelsit)
                {
                    arr[i/10]++;
                }








                for(int i=0;i<10;i++)
                {
                    dp[i]= new DataPoint(i*10,arr[i]);
                }
                series.resetData(dp);
                graph.addSeries(series);
                series.setDrawValuesOnTop(true);
                series.setSpacing(5);
                graph.getViewport().setMinX(0);
                graph.getViewport().setMaxX(100);
                graph.setTitle("Age Distribution");
                graph.getViewport().setXAxisBoundsManual(true);
                GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
                gridLabel.setHorizontalAxisTitle("Age");
            }



            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

    }

}