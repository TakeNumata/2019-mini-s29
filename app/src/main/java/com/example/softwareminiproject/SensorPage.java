package com.example.softwareminiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class SensorPage extends AppCompatActivity {

    //private volatile LineGraphSeries<DataPoint> series1;
    private volatile String state = "Oops";
    private GraphView graph;
    private LineGraphSeries series1;
    private TextView tv;
    private double x,y;
    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_page);
        this.tv = (TextView) findViewById(R.id.state_text);
        this.tv.setText(state);
        plot();
    }
        public void plot()
        {
            this.graph = (GraphView) findViewById(R.id.graph);
            this.series1 = new LineGraphSeries<>();
            this.x = 0;
            this.y = 0;
            int numDataPoints = 100;
            this.tv.setText(state);
            for (int i = 0; i < numDataPoints; i++)
            {
                x = x + 1;
                if (state == "Oops")
                {
                    y = Math.sin(x);
                }
                else if (state == "Global")
                {
                    y = x;
                }
                else if (state == "Warming")
                {
                    y = 96.8;
                }
                else
                {
                    y = 0;
                }
                series1.appendData(new DataPoint(x, y), true, 100);
                graph.addSeries(series1);
            }
        }

        public void next_state(View v)
        {
            if (state == "Oops")
                state = "Global";
            else if (state == "Global")
                state = "Warming";
            else if (state == "Warming")
                state = "Oops";
            this.graph.removeAllSeries();
            plot();
        }

        public void prev_state(View v)
        {
            if (state == "Oops")
                state = "Warming";
            else if (state == "Global")
                state = "Oops";
            else if (state == "Warming")
                state = "Global";
            this.graph.removeAllSeries();
            plot();
        }



            //graph.removeAllSeries();
            //update = new Graph_Update(series1, graph);
            //update.start();
            //tv.setText(state);
           // graph.addSeries(series1);




}
