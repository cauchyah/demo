package com.lulu.lin.mac;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class MPChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpchart);
        lineChart();
    }

    private void lineChart() {
        LineChart lineChart = (LineChart) findViewById(R.id.lineChart);
        List<Entry> enteries = new ArrayList<>();
        List<Entry> enteries2 = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j <5 ; j++) {
//                    enteries.add(new Entry((float)Math.random()+i,(float)Math.random()+j));
//                    enteries2.add(new Entry((float)Math.random()+j+1,(float)Math.random()+i+2));
//            }
//        }

        enteries.add(new Entry(1, 4));
        enteries.add(new Entry(2, 3));
        enteries.add(new Entry(3, 9));
        enteries.add(new Entry(4, 8));
        enteries.add(new Entry(5, 2));
        enteries2.add(new Entry(1, 1));
        enteries2.add(new Entry(2, 2));
        enteries2.add(new Entry(3, 7));
        enteries2.add(new Entry(4, 4));
        enteries2.add(new Entry(5, 8));

        LineDataSet dataSet = new LineDataSet(enteries, "first");


        LineDataSet dataSet2 = new LineDataSet(enteries2, "second");
        dataSet2.setColor(Color.parseColor("#666666"));
        List<ILineDataSet> iLineDataSet = new ArrayList<>();
        iLineDataSet.add(dataSet);
        iLineDataSet.add(dataSet2);


        //禁用右边y轴
        YAxis yAxis = lineChart.getAxisRight();
        yAxis.setEnabled(false);

        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setDrawGridLines(false); //不画网格线
        //水平警戒线
        LimitLine limitLine=new LimitLine(6f,"6的警戒线");
        limitLine.setLineColor(Color.RED);
        limitLine.setLineWidth(1f);
        limitLine.setTextColor(Color.BLACK);
        limitLine.setTextSize(12f);
        leftAxis.addLimitLine(limitLine);
        LimitLine limitLine2=new LimitLine(3f,"3的警戒线");
        limitLine2.setLineColor(Color.parseColor("#fd9627"));
        limitLine2.setLineWidth(1f);
        limitLine2.setTextColor(Color.BLACK);
        limitLine2.setTextSize(12f);
        leftAxis.addLimitLine(limitLine2);



        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//将x轴显示在下面
        xAxis.setDrawGridLines(false);
        IAxisValueFormatter iAxisValueFormatter=new IAxisValueFormatter() {
            private String [] dates={"06-15","06-16","06-17","06-18","06-19","06-20","06-21","06-22","06-23"
                                    ,"06-24,06-25"};
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return dates[(int)value];
            }

        };
        xAxis.setGranularity(1);//步长
        //xAxis.setValueFormatter(iAxisValueFormatter);

        Legend l=lineChart.getLegend();
        l.setFormSize(10f); // set the size of the legend forms/shapes
        l.setForm(Legend.LegendForm.LINE); // set what type of form/shape should be used
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setTextSize(12f);
        l.setTextColor(Color.BLACK);
        l.setXEntrySpace(5f); // set the space between the legend entries on the x-axis
        l.setYEntrySpace(5f);
        LineData lineData = new LineData(iLineDataSet);
//        lineChart.setHighlightPerTapEnabled(false);//去掉十字辅助线
        lineChart.setData(lineData);
        lineChart.setDescription(null);
        lineChart.invalidate();

    }

    private class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
