package com.stemlearningworld.stem_app_one;

import android.app.Service;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

public class LightMeter extends AppCompatActivity implements SensorEventListener{

    TextView textView;
    TextView textViewNoise;
    SensorManager sensorManager;
    Sensor sensor;
    static double lightInput;

    HorizontalBarChart LightChart;
    HorizontalBarChart NoiseChart;
    int optimalLux = 700;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart);


        sensorManager = (SensorManager)getSystemService(Service.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        LightChart = (HorizontalBarChart) findViewById(R.id.chart1);

    }
    @Override
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
    }
    @Override
    protected void onResume(){
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        horizontalChart((float) lightInput);

                    }
                });
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {

                }
            }
        }).start();

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==Sensor.TYPE_LIGHT){
            lightInput = event.values[0];
            textView.setText("" + String.format("%.2f", lightInput));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    private void horizontalChart(float value){
        String[] labels = {
                "Light",
                "Noise"
        };

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry EntriesLight = new BarEntry(0, (float) value); // Light value
        valueSet1.add(EntriesLight);

        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        BarEntry EntriesNoise = new BarEntry(0,optimalLux ); // Light value
        valueSet2.add(EntriesNoise);


        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Cantidad de luz actual");

        barDataSet1.setColors(new int[] { R.color.accent }, this);

        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Cantidad de luz óptima");

        barDataSet2.setColors(new int[] { R.color.audiocolor}, this);

//        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Brand 2");
//        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();

        if(lightInput>optimalLux){
            dataSets.add(barDataSet1);
            dataSets.add(barDataSet2);
        }
        else if(lightInput<optimalLux){
            dataSets.add(barDataSet2);
            dataSets.add(barDataSet1);
        }else {

                dataSets.add(barDataSet1);
                dataSets.add(barDataSet2);

        }


        BarData GoalsBardata = new BarData(dataSets);
        HorizontalBarChart chart = (HorizontalBarChart) findViewById(R.id.chart1);
        chart.setData(GoalsBardata);
        chart.animateXY(5000, 5000);
        chart.setDrawGridBackground(true);
        chart.setDrawValueAboveBar(true);
        chart.setBackgroundColor(Color.WHITE);
        chart.getXAxis().setValueFormatter(new LabelFormatter(labels));
        chart.getAxisRight().setDrawLabels(false);
        chart.getXAxis().setDrawLabels(false);
        chart.getAxisLeft().setAxisMinimum(0);
        chart.getAxisRight().setAxisMinimum((float) (lightInput + lightInput * 0.2));
        chart.notifyDataSetChanged();
        chart.invalidate();
    }

    //**************NoiseChart**********************
    private void horizontalChartNoise(float value){
        String[] labels = {
                "Light",
                "Noise"
        };

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry EntriesLight = new BarEntry(0, (float) value); // Light value
        valueSet1.add(EntriesLight);

        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        BarEntry EntriesNoise = new BarEntry(0,optimalLux ); // Light value
        valueSet2.add(EntriesNoise);


        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Cantidad de luz actual");

        barDataSet1.setColors(new int[] { R.color.accent }, this);

        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Cantidad de luz óptima");

        barDataSet2.setColors(new int[] { R.color.audiocolor}, this);

//        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Brand 2");
//        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();

        BarData GoalsBardata = new BarData(dataSets);
        HorizontalBarChart chartNoise = (HorizontalBarChart) findViewById(R.id.chart1);
        chartNoise.setData(GoalsBardata);
        chartNoise.animateXY(5000, 5000);
        chartNoise.setDrawGridBackground(true);
        chartNoise.setDrawValueAboveBar(true);
        chartNoise.setBackgroundColor(Color.WHITE);
        chartNoise.getXAxis().setValueFormatter(new LabelFormatter(labels));
        chartNoise.getAxisRight().setDrawLabels(false);
        chartNoise.getXAxis().setDrawLabels(false);
        chartNoise.getAxisLeft().setAxisMinimum(0);
        chartNoise.getAxisRight().setAxisMinimum((float) (lightInput + lightInput * 0.2));
        chartNoise.notifyDataSetChanged();
        chartNoise.invalidate();
    }
    //*************End NoiseChart*******************
    public class LabelFormatter implements IAxisValueFormatter {
        private final String[] mLabels;

        public LabelFormatter(String[] labels) {
            mLabels = labels;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mLabels[(int) value];
        }
    }


}
