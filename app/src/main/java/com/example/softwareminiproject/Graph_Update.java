package com.example.softwareminiproject;

import android.view.View;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Graph_Update extends Thread {
    private GraphView graph;
    private LineGraphSeries<DataPoint> series1;
    public Graph_Update(LineGraphSeries<DataPoint> Series1, GraphView Graph)
    {
       this.series1=Series1;
       this.graph = Graph;
    }
    private volatile String state = "A";
    private volatile boolean loop = false;
    private double x = 0;
    private double y = 0;
    @Override
    public void run()
    {
            int numDataPoints = 500;
            for (int i = 0; i < numDataPoints; i++) {
                x = x + 0.1;
                if (state == "A") {
                    y = Math.sin(x);
                } else if (state == "B") {
                    y = x;
                } else if (state == "C") {
                    y = 96.8;
                } else
                    y = 0;

                series1.appendData(new DataPoint(x, y), true, 100);
                graph.addSeries(series1);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

    }

    public void next_state(View v)
    {
        loop = true;
        if (state == "A")
            state = "B";
        else if (state == "B")
            state = "C";
        else if (state == "C")
            state = "A";
    }

    public void prev_state(View v)
    {
        loop = true;
        if (state == "A")
            state = "C";
        else if (state == "A")
            state = "C";
        else if (state == "C")
            state = "B";
    }
}
