package com.kbk.practice_dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SalesActivity extends AppCompatActivity {

    Button btn_back;

    LineChart lineChart;

    String[] weekdays = {"1월","2월","3월","4월","5월"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        lineChart = (LineChart) findViewById(R.id.Chart);

        btn_back = (Button) findViewById(R.id.btn_sales_back);

        SetChart(); //매출차트 실행함수.


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { SalesActivity.super.onBackPressed(); }
        });

    }

    private void SetChart() {
        //차트 초기화 작업.
        lineChart.invalidate();
        lineChart.clear();

        //차트 데이터 셋에 담겨질 데이터.
        //Entry 라이브러리 이용해서 데이터리스트 생성.
        ArrayList<Entry> entry_chart = new ArrayList<>();
        ArrayList<Entry> entry_chart2 = new ArrayList<>();

        LineData chartData = new LineData(); //LineDataSet을 담는 그릇. 여러개의 라인 ChartData 담을 수 있음.

        //데이터 생성
        entry_chart.add(new Entry(0, 0));
        entry_chart.add(new Entry(100, 59));
        entry_chart.add(new Entry(200, 137));
        entry_chart.add(new Entry(300, 212));
        entry_chart.add(new Entry(400, 252));
        entry_chart.add(new Entry(500, 278));

        entry_chart2.add(new Entry(0,0));
        entry_chart2.add(new Entry(100,232));
        entry_chart2.add(new Entry(200,32));

        //상품1
        LineDataSet lineDataSet = new LineDataSet(entry_chart, "기본 옷걸이 1호");
        lineDataSet.setColor(Color.GREEN); //LineChart에서 Line Color 설정
        lineDataSet.setLineWidth(2);
        lineDataSet.setValueTextSize(20);
        //상품2
        LineDataSet lineDataSet2 = new LineDataSet(entry_chart2, "기본 옷걸이 2호");
        lineDataSet2.setColor(Color.BLACK);
        lineDataSet2.setLineWidth(2);
        lineDataSet2.setValueTextSize(20);

        YAxis yAxisRight = lineChart.getAxisRight(); //오른쪽 단위는 흰색으로 만들어 안보이게 함.
        yAxisRight.setTextColor(R.color.mainbackground);


        chartData.addDataSet(lineDataSet);
        chartData.addDataSet(lineDataSet2);

        // x 축 설정(날짜)
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setTextSize(20);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); //X축 레이블 위치를 차트 아래로.



        //y축 설정(매출)
        YAxis yAxisLeft = lineChart.getAxisLeft();
        yAxisLeft.setTextSize(20);

        lineChart.setData(chartData);
    }


}

