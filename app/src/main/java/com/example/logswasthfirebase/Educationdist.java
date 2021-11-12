package com.example.logswasthfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Educationdist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

        FirebaseDatabase rootNode;
        DatabaseReference reference;
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("villagers");



        PieChart pieChart;
        pieChart = findViewById(R.id.pieChart_view);



        ArrayList<String> Edulist = new ArrayList<String>();

        int [] Education = new int[6];

        for(int i =0; i<6; i++)
        {
            Education[i]=0;
        }
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot mydataSnapshot : dataSnapshot.getChildren()){
                    VillagerHelperClass villagersdetails = mydataSnapshot.getValue(VillagerHelperClass.class);

                    Edulist.add(villagersdetails.getEducation());

                }



                for(String s : Edulist)
                {
                    if(s.equals("Higher Secondary Certificate"))
                    {
                        Education[1]++;
                    }
                    if(s.equals("Senior Secondary Certificate"))
                    {
                        Education[2]++;
                    }
                    if(s.equals("Undergraduation"))
                    {
                        Education[3]++;
                    }
                    if(s.equals("Postgraduation"))
                    {
                        Education[4]++;
                    }
                    if(s.equals("Other"))
                    {
                        Education[5]++;
                    }
                }






                ArrayList<PieEntry> pieEntries = new ArrayList<>();
                String label = "type";

                //initializing data
                Map<String, Integer> typeAmountMap = new HashMap<>();
                typeAmountMap.put("Higher Secondary Certificate",Education[1]);
                typeAmountMap.put("Senior Secondary Certificate",Education[2]);
                typeAmountMap.put("Undergraduation",Education[3]);
                typeAmountMap.put("Postgraduation",Education[4]);
                typeAmountMap.put("Other",Education[5]);

                //initializing colors for the entries
                ArrayList<Integer> colors = new ArrayList<>();
                colors.add(Color.parseColor("#304567"));
                colors.add(Color.parseColor("#309967"));
                colors.add(Color.parseColor("#476567"));
                colors.add(Color.parseColor("#890567"));
                colors.add(Color.parseColor("#a35567"));
                colors.add(Color.parseColor("#ff5f67"));
                colors.add(Color.parseColor("#3ca567"));

                //input data and fit data into pie chart entry
                for(String type: typeAmountMap.keySet()){
                    pieEntries.add(new PieEntry(typeAmountMap.get(type).floatValue(), type));
                }

                //collecting the entries with label name
                PieDataSet pieDataSet = new PieDataSet(pieEntries,label);
                //setting text size of the value
                pieDataSet.setValueTextSize(12f);
                //providing color list for coloring different entries
                pieDataSet.setColors(colors);
                //grouping the data set from entry to chart
                PieData pieData = new PieData(pieDataSet);
                //showing the value of the entries, default true if not set
                pieData.setDrawValues(true);

                pieChart.setData(pieData);
                pieChart.invalidate();


            }



            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


    }
}