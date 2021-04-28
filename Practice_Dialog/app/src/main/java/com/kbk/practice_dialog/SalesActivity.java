package com.kbk.practice_dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
    Button btn_modify;

    LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        FindID(); //id매칭
        SetChart(); //매출차트 실행함수.
        ButtonFuntion(); //버튼 기능.
    }

    private void ButtonFuntion() {
        btn_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"수정 기능 준비중...", Toast.LENGTH_SHORT).show();
            }
        });

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
        ArrayList<Entry> entry_chart3 = new ArrayList<>();
        ArrayList<Entry> entry_chart4 = new ArrayList<>();

        LineData chartData = new LineData(); //LineDataSet을 담는 그릇. 여러개의 라인 ChartData 담을 수 있음.

        //데이터 생성
        entry_chart.add(new Entry(16, 20));
        entry_chart.add(new Entry(17, 59));
        entry_chart.add(new Entry(18, 137));
        entry_chart.add(new Entry(19, 212));
        entry_chart.add(new Entry(20, 252));
        entry_chart.add(new Entry(21, 278));

        entry_chart2.add(new Entry(16,16));
        entry_chart2.add(new Entry(17,20));
        entry_chart2.add(new Entry(18,59));
        entry_chart2.add(new Entry(19, 97));
        entry_chart2.add(new Entry(20, 140));
        entry_chart2.add(new Entry(21, 160));

        entry_chart3.add(new Entry(16,30));
        entry_chart3.add(new Entry(17,10));
        entry_chart3.add(new Entry(18,85));
        entry_chart3.add(new Entry(19, 86));
        entry_chart3.add(new Entry(20, 91));
        entry_chart3.add(new Entry(21, 68));

        entry_chart4.add(new Entry(16,13));
        entry_chart4.add(new Entry(17,40));
        entry_chart4.add(new Entry(18,180));
        entry_chart4.add(new Entry(19, 150));
        entry_chart4.add(new Entry(20, 190));
        entry_chart4.add(new Entry(21, 101));


        //상품1
        LineDataSet lineDataSet = new LineDataSet(entry_chart, "기본 옷걸이 1호");
        lineDataSet.setColor(Color.GREEN); //LineChart에서 Line Color 설정
        lineDataSet.setLineWidth(2);
        lineDataSet.setValueTextSize(10);
        //상품2
        LineDataSet lineDataSet2 = new LineDataSet(entry_chart2, "기본 옷걸이 2호");
        lineDataSet2.setColor(Color.BLACK);
        lineDataSet2.setLineWidth(2);
        lineDataSet2.setValueTextSize(10);
        //상품3
        LineDataSet lineDataSet3 = new LineDataSet(entry_chart3, "집게 옷걸이 1호");
        lineDataSet3.setColor(Color.BLUE);
        lineDataSet3.setLineWidth(2);
        lineDataSet3.setValueTextSize(10);
        //상품4
        LineDataSet lineDataSet4 = new LineDataSet(entry_chart4, "집게 옷걸이 2호");
        lineDataSet4.setColor(Color.RED);
        lineDataSet4.setLineWidth(2);
        lineDataSet4.setValueTextSize(10);

        YAxis yAxisRight = lineChart.getAxisRight();
        yAxisRight.setTextColor(Color.WHITE);   //오른쪽 단위는 흰색으로 만들어 안보이게 함.


        chartData.addDataSet(lineDataSet);
        chartData.addDataSet(lineDataSet2);
        chartData.addDataSet(lineDataSet3);
        chartData.addDataSet(lineDataSet4);

        // x 축 설정(날짜)
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setTextSize(10);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); //X축 레이블 위치를 차트 아래로.



        //y축 설정(매출)
        YAxis yAxisLeft = lineChart.getAxisLeft();
        yAxisLeft.setTextSize(20);

        lineChart.setData(chartData);
    }

    private void FindID() {
        btn_modify = (Button) findViewById(R.id.btn_sales_mdfy);
        btn_back = (Button) findViewById(R.id.btn_sales_back);

        lineChart = (LineChart) findViewById(R.id.Chart);
    }

}

